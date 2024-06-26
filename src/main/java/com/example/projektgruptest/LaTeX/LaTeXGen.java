package LaTeXGen;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class LaTeXGen {
    private String originFolderPath; // Folder ze zrodlami
    private String originTexPath; // Sciezka do origin pliku LaTeX
    private String originClsPath; // Sciezka do origin bibloteki

    public LaTeXGen() {
    }

    /**
     * @brief Ustaw folder origin.
     * Ustaw folder posiadajacy glowne zrodla plikow,
     * w celu pozniejszego multigenerowania nowych PDFow.
     * @param originFolderPath
     */
    public void setOriginPaths(String originFolderPath) {
        this.originFolderPath = originFolderPath;
        this.originTexPath = originFolderPath + "/template.tex";
        this.originClsPath = originFolderPath + "/CSDrakeCertificate.cls";
    }

    /**
     * @brief Zwroc origin
     * Zwroc sciezke do folderu origin
     * @return folder origin
     */
    public String getOriginFolderPath()
    {
        return originFolderPath;
    }

    /**
     * @brief Kopiuje origin do innego folderu.
     * Kopiuje zawartosc ustawionego origin do folderu z parametru funkcji.
     * @param destinationFolderPath Folder docelowy kopii.
     */
    public void copyFilesToDestination(String destinationFolderPath) {
        try {
            Files.createDirectories(Paths.get(destinationFolderPath));

            File originTexFile = new File(originTexPath);
            File originClsFile = new File(originClsPath);

            File destinationTexFile = new File(destinationFolderPath + "/template.tex");
            File destinationClsFile = new File(destinationFolderPath + "/CSDrakeCertificate.cls");

            FileInputStream fisTex = new FileInputStream(originTexFile);
            FileOutputStream fosTex = new FileOutputStream(destinationTexFile);

            FileInputStream fisCls = new FileInputStream(originClsFile);
            FileOutputStream fosCls = new FileOutputStream(destinationClsFile);

            byte[] buffer = new byte[1024];
            int length;
            while ((length = fisTex.read(buffer)) > 0) {
                fosTex.write(buffer, 0, length);
            }
            fisTex.close();
            fosTex.close();

            while ((length = fisCls.read(buffer)) > 0) {
                fosCls.write(buffer, 0, length);
            }
            fisCls.close();
            fosCls.close();

            System.out.println("Files copied successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @brief Modyfikacja slowa wewnatrz pliku LaTeX.
     * Pozwala zamienic wybrane slowo na nowe w pliku LaTeX.
     * Wykorzystywane do parametryzowania dokumentu i generowania go na bazie nowych danych.
     * @param filePath Sciezka do pliku LaTeX.
     * @param oldWord Slowo do zastapienia.
     * @param newWord Nowe Slowo.
     */
    public void replaceWordInFile(String filePath, String oldWord, String newWord) {
        try {
            // Otwarcie pliku do odczytu
            File file = new File(filePath + "/template.tex");
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            // Odczytanie zawartości pliku wiersz po wierszu
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                // Zamiana pierwszego wystąpienia oldWord na newWord w aktualnej linii
                line = line.replaceFirst(oldWord, newWord);
                // Dodanie zmodyfikowanej linii do stringa
                stringBuilder.append(line);
                stringBuilder.append(System.lineSeparator());
            }

            // Zamknięcie strumieni do odczytu
            bufferedReader.close();
            fileReader.close();

            // Zapisanie zmodyfikowanej zawartości do pliku
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(stringBuilder.toString());

            // Zamknięcie strumieni do zapisu
            bufferedWriter.close();
            fileWriter.close();

            System.out.println("Word replaced successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @brief Kompilacja dokumentu.
     * Kompiluje wybrany plik LaTeX do PDF.
     * @param sourceFolderPath Scieżka do pliku LaTeX.
     * @param outputFolderPath Sciezka docelowa dla wyniku kompilacji.
     */
    public void compileToPDF(String sourceFolderPath, String outputFolderPath) {
        try {
            // Tworzenie procesu kompilacji LaTeX
            ProcessBuilder processBuilder = new ProcessBuilder("pdflatex", "-include-directory=source", "template.tex");
            // Ustawienie katalogu źródłowego dla procesu
            processBuilder.directory(new File(sourceFolderPath));
            // Przekierowanie wyjścia i błędów do pliku kompilacji.log w katalogu źródłowym
            processBuilder.redirectErrorStream(true);
            processBuilder.redirectOutput(new File(sourceFolderPath + "/kompilacja.log"));
            // Uruchomienie procesu
            Process process = processBuilder.start();
            // Oczekiwanie na zakończenie procesu
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                System.out.println("Compilation successful.");
                // Przeniesienie wynikowego pliku PDF do folderu docelowego
                File pdfFile = new File(sourceFolderPath + "/template.pdf");
                pdfFile.renameTo(new File(outputFolderPath + "/template.pdf"));
                System.out.println("PDF file moved to output folder.");
            } else {
                System.err.println("Compilation failed. See kompilacja.log for details.");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * @brief Usuwa pliki w folderze.
     * Funkcja czyszcząca pliki znajdujące się w folderze.
     * @param folderPath Ścieżka do folderu.
     */
    public void deleteFilesInFolder(String folderPath) {
        File folder = new File(folderPath);
        File[] files = folder.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    if (!file.delete()) {
                        System.err.println("Failed to delete file: " + file.getName());
                    }
                }
            }
            System.out.println("All files in folder deleted successfully.");
        } else {
            System.err.println("Folder is empty or does not exist.");
        }
    }
}
