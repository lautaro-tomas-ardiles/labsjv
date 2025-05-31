import java.util.Scanner;

public class morse {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        String[] dictionaryMorse = {
                ".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....",
                "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.",
                "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-",
                "-.--", "--.."
        };

        char[] dictionary = {
                'a','b','c','d','e','f','g','h','i','j','k','l','m',
                'n','o','p','q','r','s','t','u','v','w','x','y','z'
        };

        System.out.println("¿vas a traducir morse a letras? (si | no) (si lo hace sepárelas con ,) : ");
        String morse = in.nextLine();
        morse = morse.trim();

        System.out.println("ingrese el texto a transformar : ");
        String text = in.nextLine();
        text = text.trim();

        StringBuilder translate = new StringBuilder();

        switch (morse) {
            case "no":
                for (char i : text.toCharArray()) {
                    int index; //se define indice

                    //se recorre dictionary
                    for (int j = 0; j < dictionary.length; j++) {
                        //si el character es igual a la posición en dictionary
                        //se guarda como índice
                        if (i == dictionary[j]) {
                            index = j;
                            translate.append(dictionaryMorse[index]).append(",");
                            break;
                        }
                    }
                }
                System.out.println(translate);
            break;
            case "si" :
                String[] newText = text.trim().split(",");// se separa letra a letra para morse

                for (String i : newText) {// (Character i : Arrays.toString(newText).toCharArray()) { alternativa para trabajar con indice char
                    int index ;

                    //se recorre dictionary
                    for (int j = 0; j < dictionaryMorse.length; j++) {

                        if (i.equals(dictionaryMorse[j])) {
                            index = j;
                            translate.append(dictionary[index]).append(",");
                            break;
                        }
                    }
                }
                System.out.println(translate);
            break;
            default: System.out.println("las opciones son si o no");
        }
    }
}