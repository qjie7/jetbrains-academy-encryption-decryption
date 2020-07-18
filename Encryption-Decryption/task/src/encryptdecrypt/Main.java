package encryptdecrypt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;



class ShiftEncryptMethod implements Main.EncryptMethod {
    @Override
    public char[] encrypt(String message, String key) {
        char[] arr = new char[message.length()];
        String regex = "\\W";
        for (int i = 0; i < message.length(); i++) {
            if (String.valueOf(message.charAt(i)).matches(regex)) {
                arr[i] = message.charAt(i);
                continue;
            } else {
                char c = message.charAt(i);
                int originalAlphabetPosition = c - 'a';
                int newAlphabetPosition = (originalAlphabetPosition + Integer.parseInt(key)) % 26;
                char newCharacter = (char) ('a' + newAlphabetPosition);
                arr[i] = newCharacter;
            }
        }
        return arr;
    }
}

class ShiftDecryptMethod implements Main.DecryptMethod {

    @Override
    public char[] decrypt(String message, String key) {
        char[] arr = new char[message.length()];
        String regex = "\\W";
        for (int i = 0; i < message.length(); i++) {
            if (String.valueOf(message.charAt(i)).matches(regex)) {
                arr[i] = message.charAt(i);
                continue;
            } else {
                char c = message.charAt(i);
                int originalAlphabetPosition = c - 'a';
                int newAlphabetPosition = (originalAlphabetPosition + (26 - (Integer.parseInt(key) % 26))) % 26;
                char newCharacter = (char) ('a' + newAlphabetPosition);
                arr[i] = newCharacter;
            }
        }
        return arr;
    }
}

class UnicodeEncryptMethod implements Main.EncryptMethod {

    @Override
    public char[] encrypt(String message, String key) {
        char[] encryptedMessage = new char[message.length()];
        for (int i = 0; i < message.length(); i++) {
            char c = message.charAt(i);
            c = (char)(c + Integer.parseInt(key));
            encryptedMessage[i] = c;
        }
        return encryptedMessage;
    }
}

class UnicodeDecryptMethod implements Main.DecryptMethod {
    @Override
    public char[] decrypt(String message, String key) {
        char[] encryptedMessage = new char[message.length()];
        for (int i = 0; i < message.length(); i++) {
            char c = message.charAt(i);
            c = (char)(c - Integer.parseInt(key));
            encryptedMessage[i] = c;
        }
        return encryptedMessage;
    }
}

class AlgoSelector {
    private Main.EncryptMethod encryptMethod;
    private Main.DecryptMethod decryptMethod;

    public void setEncryptMethod (Main.EncryptMethod encryptMethod) {
        this.encryptMethod = encryptMethod;
    }

    public void setDecryptMethod (Main.DecryptMethod decryptMethod) {
        this.decryptMethod = decryptMethod;
    }

    public char[] runEncrypt(String message, String key) {
        return this.encryptMethod.encrypt(message, key);
    }

    public char[] runDecrypt(String message, String key) {
        return this.decryptMethod.decrypt(message, key);
    }
}

public class Main {

    interface EncryptMethod {
        char[] encrypt(String message, String key);
    }

    interface DecryptMethod {
        char[] decrypt(String message, String key);
    }

    public static void main(String[] args) {
        AlgoSelector algoSelector = new AlgoSelector();
        HashMap<String, String> map = new HashMap<>();
        for (int i = 0; i < args.length - 1; i += 2) {
            map.put(args[i], args[i + 1]);
        }

        String key = map.getOrDefault("-key", "0");

        if (map.getOrDefault("-mode", "enc").equals("enc")) {
            if (map.containsKey("-data")) {
                String message = map.getOrDefault("-data", "");
                if (map.containsKey("-out")) {
                    File fileOut = new File(map.get("-out"));
                    if (map.getOrDefault("-alg", "shift").equals("shift")) {
                        algoSelector.setEncryptMethod(new ShiftEncryptMethod());
                    } else if (map.get("-alg").equals("unicode")) {
                        algoSelector.setEncryptMethod(new UnicodeEncryptMethod());
                    }
                    try (FileWriter writer = new FileWriter(fileOut)) {
                        writer.write(algoSelector.runEncrypt(message, key));
                    } catch (IOException e) {
                        System.out.printf("Error, An exception occurs %s", e.getMessage());
                    }
                } else {
                    System.out.println(algoSelector.runEncrypt(message, key));
                }

            } else if (map.containsKey("-in")) {
                File file = new File(map.get("-in"));
                try (Scanner scanner = new Scanner(file) ) {
                    String message = scanner.nextLine();

                    if (map.containsKey("-out")) {
                        File fileOut = new File(map.get("-out"));
                        if (map.getOrDefault("-alg", "shift").equals("shift")) {
                            algoSelector.setEncryptMethod(new ShiftEncryptMethod());
                        } else if (map.get("-alg").equals("unicode")) {
                            algoSelector.setEncryptMethod(new UnicodeEncryptMethod());
                        }
                        try (FileWriter writer = new FileWriter(fileOut)) {
                            writer.write(algoSelector.runEncrypt(message, key));
                        } catch (IOException e) {
                            System.out.printf("Error, An exception occurs %s", e.getMessage());
                        }
                    } else {
                        System.out.println(algoSelector.runEncrypt(message, key));
                    }

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } else {
                String message = "";
                if (map.containsKey("-out")) {
                    File fileOut = new File(map.get("-out"));
                    if (map.getOrDefault("-alg", "shift").equals("shift")) {
                        algoSelector.setEncryptMethod(new ShiftEncryptMethod());
                    } else if (map.get("-alg").equals("unicode")) {
                        algoSelector.setEncryptMethod(new UnicodeEncryptMethod());
                    }
                    try (FileWriter writer = new FileWriter(fileOut)) {
                        writer.write(algoSelector.runEncrypt(message, key));
                    } catch (IOException e) {
                        System.out.printf("Error, An exception occurs %s", e.getMessage());
                    }
                } else {
                    System.out.println(algoSelector.runEncrypt(message, key));
                }

            }

        } else if (map.getOrDefault("-mode", "enc").equals("dec")) {
            if (map.containsKey("-data")) {
                String message = map.getOrDefault("-data", "");
                // String key = map.getOrDefault("-key", "0");
                if (map.containsKey("-out")) {
                    File fileOut = new File(map.get("-out"));
                    if (map.getOrDefault("-alg", "shift").equals("shift")) {
                        algoSelector.setDecryptMethod(new ShiftDecryptMethod());
                    } else if (map.get("-alg").equals("unicode")) {
                        algoSelector.setDecryptMethod(new UnicodeDecryptMethod());
                    }
                    try (FileWriter writer = new FileWriter(fileOut)) {
                        writer.write(algoSelector.runDecrypt(message, key));
                    } catch (IOException e) {
                        System.out.printf("Error, An exception occurs %s", e.getMessage());
                    }
                } else {
                    System.out.println(algoSelector.runDecrypt(message, key));
                }

            } else if (map.containsKey("-in")) {
                File file = new File(map.get("-in"));
                try (Scanner scanner = new Scanner(file) ) {
                    String message = scanner.nextLine();

                    if (map.containsKey("-out")) {
                        File fileOut = new File(map.get("-out"));
                        if (map.getOrDefault("-alg", "shift").equals("shift")) {
                            algoSelector.setDecryptMethod(new ShiftDecryptMethod());
                        } else if (map.get("-alg").equals("unicode")) {
                            algoSelector.setDecryptMethod(new UnicodeDecryptMethod());
                        }
                        try (FileWriter writer = new FileWriter(fileOut)) {
                            writer.write(algoSelector.runDecrypt(message, key));
                        } catch (IOException e) {
                            System.out.printf("Error, An exception occurs %s", e.getMessage());
                        }
                    } else {
                        System.out.println(algoSelector.runDecrypt(message, key));
                    }

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } else {
                String message = "";
                if (map.containsKey("-out")) {
                    File fileOut = new File(map.get("-out"));
                    if (map.getOrDefault("-alg", "shift").equals("shift")) {
                        algoSelector.setDecryptMethod(new ShiftDecryptMethod());
                    } else if (map.get("-alg").equals("unicode")) {
                        algoSelector.setDecryptMethod(new UnicodeDecryptMethod());
                    }
                    try (FileWriter writer = new FileWriter(fileOut)) {
                        writer.write(algoSelector.runDecrypt(message, key));
                    } catch (IOException e) {
                        System.out.printf("Error, An exception occurs %s", e.getMessage());
                    }
                } else {
                    System.out.println(algoSelector.runDecrypt(message, key));
                }

            }

        }
    }
}
