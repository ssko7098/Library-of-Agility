package Lab29.Huaicheng.Group1.A2;

import java.util.ArrayList;
import java.util.HashMap;

public class AsciiArt {

    public ArrayList<StringBuilder> textToPrint;

    public String line1 = "         888                   888           .d888         888      d8b         d8b 888      888                                                                    888                                                                           d8888 888888b.    .d8888b.  8888888b.  8888888888 8888888888 .d8888b.  888    888 8888888        888888 888    d8P  888      888b     d888 888b    888  .d88888b.  8888888b.   .d88888b.  8888888b.   .d8888b. 88888888888 888     888 888     888 888       888 Y88b   d88P Y88b   d88P 8888888888P  d888    .d8888b.   .d8888b.      d8888  888888888   .d8888b. 8888888888  .d8888b.   .d8888b.   .d8888b.  ";
    public String line2 = "         888                   888          d88P\"          888      Y8P         Y8P 888      888                                                                    888                                                                          d88888 888  \"88b  d88P  Y88b 888  \"Y88b 888        888       d88P  Y88b 888    888   888            \"88b 888   d8P   888      8888b   d8888 8888b   888 d88P\" \"Y88b 888   Y88b d88P\" \"Y88b 888   Y88b d88P  Y88b    888     888     888 888     888 888   o   888  Y88b d88P   Y88b d88P        d88P  d8888   d88P  Y88b d88P  Y88b    d8P888  888        d88P  Y88b      d88P d88P  Y88b d88P  Y88b d88P  Y88b ";
    public String line3 = "         888                   888          888            888                      888      888                                                                    888                                                                         d88P888 888  .88P  888    888 888    888 888        888       888    888 888    888   888             888 888  d8P    888      88888b.d88888 88888b  888 888     888 888    888 888     888 888    888 Y88b.         888     888     888 888     888 888  d8b  888   Y88o88P     Y88o88P        d88P     888          888      .d88P   d8P 888  888        888            d88P  Y88b. d88P 888    888 888    888 ";
    public String line4 = " 8888b.  88888b.   .d8888b .d88888  .d88b.  888888 .d88b.  88888b.  888        8888 888  888 888 88888b.d88b.  88888b.   .d88b.  88888b.   .d88888 888d888 .d8888b  888888 888  888 888  888 888  888  888 888  888 888  888 88888888          d88P 888 8888888K.  888        888    888 8888888    8888888   888        8888888888   888             888 888d88K     888      888Y88888P888 888Y88b 888 888     888 888   d88P 888     888 888   d88P  \"Y888b.      888     888     888 Y88b   d88P 888 d888b 888    Y888P       Y888P        d88P      888        .d88P     8888\"   d8P  888  8888888b.  888d888b.     d88P    \"Y88888\"  Y88b. d888 888    888 ";
    public String line5 = "    \"88b 888 \"88b d88P\"   d88\" 888 d8P  Y8b 888   d88P\"88b 888 \"88b 888        \"888 888 .88P 888 888 \"888 \"88b 888 \"88b d88\"\"88b 888 \"88b d88\" 888 888P\"   88K      888    888  888 888  888 888  888  888 `Y8bd8P' 888  888    d88P          d88P  888 888  \"Y88b 888        888    888 888        888       888  88888 888    888   888             888 8888888b    888      888 Y888P 888 888 Y88b888 888     888 8888888P\"  888     888 8888888P\"      \"Y88b.    888     888     888  Y88b d88P  888d88888b888    d888b        888        d88P       888    .od888P\"       \"Y8b. d88   888       \"Y88b 888P \"Y88b 88888888  .d8P\"\"Y8b.  \"Y888P888 888    888 ";
    public String line6 = ".d888888 888  888 888     888  888 88888888 888   888  888 888  888 888         888 888888K  888 888  888  888 888  888 888  888 888  888 888  888 888     \"Y8888b. 888    888  888 Y88  88P 888  888  888   X88K   888  888   d88P          d88P   888 888    888 888    888 888    888 888        888       888    888 888    888   888             888 888  Y88b   888      888  Y8P  888 888  Y88888 888     888 888        888 Y8b 888 888 T88b         \"888    888     888     888   Y88o88P   88888P Y88888   d88888b       888       d88P        888   d88P\"      888    888 8888888888        888 888    888  d88P     888    888        888 888    888 ";
    public String line7 = "888  888 888 d88P Y88b.   Y88b 888 Y8b.     888   Y88b 888 888  888 888         888 888 \"88b 888 888  888  888 888  888 Y88..88P 888 d88P Y88b 888 888          X88 Y88b.  Y88b 888  Y8bd8P  Y88b 888 d88P .d8\"\"8b. Y88b 888  d88P          d8888888888 888   d88P Y88b  d88P 888  .d88P 888        888       Y88b  d88P 888    888   888             88P 888   Y88b  888      888   \"   888 888   Y8888 Y88b. .d88P 888        Y88b.Y8b88P 888  T88b  Y88b  d88P    888     Y88b. .d88P    Y888P    8888P   Y8888  d88P Y88b      888      d88P         888   888\"       Y88b  d88P       888  Y88b  d88P Y88b  d88P d88P      Y88b  d88P Y88b  d88P Y88b  d88P ";
    public String line8 = "\"Y888888 88888P\"   \"Y8888P \"Y88888  \"Y8888  888    \"Y88888 888  888 888         888 888  888 888 888  888  888 888  888  \"Y88P\"  88888P\"   \"Y88888 888      88888P'  \"Y888  \"Y88888   Y88P    \"Y8888888P\"  888  888  \"Y88888 88888888      d88P     888 8888888P\"   \"Y8888P\"  8888888P\"  8888888888 888        \"Y8888P88 888    888 8888888           888 888    Y88b 88888888 888       888 888    Y888  \"Y88888P\"  888         \"Y888888\"  888   T88b  \"Y8888P\"     888      \"Y88888P\"      Y8P     888P     Y888 d88P   Y88b     888     d8888888888 8888888 888888888   \"Y8888P\"        888   \"Y8888P\"   \"Y8888P\" d88P        \"Y8888P\"   \"Y8888P\"   \"Y8888P\"  ";
    public String line9 = "                                                       888                      888                                              888           888                                                                       888                                                                                                                        .d88P                                                                              Y8b                                                                                                                                                                                                                       ";
    public String line10 = "                                                  Y8b d88P                     d88P                                              888           888                                                                  Y8b d88P                                                                                                                      .d88P\"                                                                                                                                                                                                                                                                                                         ";
    public String line11 = "                                                   \"Y88P\"                    888P\"                                               888           888                                                                   \"Y88P\"                                                                                                                      888P\"                                                                                                                                                                                                                                                                                                           ";

    StringBuilder sb1 = new StringBuilder();
    StringBuilder sb2 = new StringBuilder();
    StringBuilder sb3 = new StringBuilder();
    StringBuilder sb4 = new StringBuilder();
    StringBuilder sb5 = new StringBuilder();
    StringBuilder sb6 = new StringBuilder();
    StringBuilder sb7 = new StringBuilder();
    StringBuilder sb8 = new StringBuilder();
    StringBuilder sb9 = new StringBuilder();
    StringBuilder sb10 = new StringBuilder();
    StringBuilder sb11 = new StringBuilder();
    private HashMap<Character, Integer> startOfLetter;
    private HashMap<Character, Integer> endOfLetter;

    public AsciiArt() {
        this.startOfLetter = new HashMap<>();
        this.endOfLetter = new HashMap<>();
        startOfLetter.put('a', 0);
        endOfLetter.put('a',8);

        startOfLetter.put('b',9);
        endOfLetter.put('b',17);

        startOfLetter.put('c',18);
        endOfLetter.put('c',26);

        startOfLetter.put('d',26);
        endOfLetter.put('d',34);

        startOfLetter.put('e',35);
        endOfLetter.put('e',43);

        startOfLetter.put('f',44);
        endOfLetter.put('f',50);

        startOfLetter.put('g',50);
        endOfLetter.put('g',58);

        startOfLetter.put('h',59);
        endOfLetter.put('h',67);

        startOfLetter.put('i',68);
        endOfLetter.put('i',71);

        startOfLetter.put('j',77);
        endOfLetter.put('j',83);

        startOfLetter.put('k',84);
        endOfLetter.put('k',92);

        startOfLetter.put('l',93);
        endOfLetter.put('l',96);

        startOfLetter.put('m',97);
        endOfLetter.put('m',110);

        startOfLetter.put('n',111);
        endOfLetter.put('n',119);

        startOfLetter.put('o',120);
        endOfLetter.put('o',128);

        startOfLetter.put('p',129);
        endOfLetter.put('p',137);

        startOfLetter.put('q',138);
        endOfLetter.put('q',146);

        startOfLetter.put('r',147);
        endOfLetter.put('r',154);

        startOfLetter.put('s',155);
        endOfLetter.put('s',163);

        startOfLetter.put('t',164);
        endOfLetter.put('t',170);

        startOfLetter.put('u',171);
        endOfLetter.put('u',179);

        startOfLetter.put('v',180);
        endOfLetter.put('v',188);

        startOfLetter.put('w',189);
        endOfLetter.put('w',202);

        startOfLetter.put('x',203);
        endOfLetter.put('x',211);

        startOfLetter.put('y',212);
        endOfLetter.put('y',220);

        startOfLetter.put('z',221);
        endOfLetter.put('z',229);

        startOfLetter.put('A',235);
        endOfLetter.put('A',247);

        startOfLetter.put('B',248);
        endOfLetter.put('B',258);

        startOfLetter.put('C',259);
        endOfLetter.put('C',269);

        startOfLetter.put('D',270);
        endOfLetter.put('D',280);

        startOfLetter.put('E',281);
        endOfLetter.put('E',291);

        startOfLetter.put('F',292);
        endOfLetter.put('F',301);

        startOfLetter.put('G',302);
        endOfLetter.put('G',312);

        startOfLetter.put('H',313);
        endOfLetter.put('H',323);

        startOfLetter.put('I',324);
        endOfLetter.put('I',331);

        startOfLetter.put('J',337);
        endOfLetter.put('J',345);

        startOfLetter.put('K',346);
        endOfLetter.put('K',356);

        startOfLetter.put('L',357);
        endOfLetter.put('L',366);

        startOfLetter.put('M',367);
        endOfLetter.put('M',380);

        startOfLetter.put('N',381);
        endOfLetter.put('N',392);

        startOfLetter.put('O',393);
        endOfLetter.put('O',404);

        startOfLetter.put('P',405);
        endOfLetter.put('P',415);

        startOfLetter.put('Q',416);
        endOfLetter.put('Q',427);

        startOfLetter.put('R',428);
        endOfLetter.put('R',438);

        startOfLetter.put('S',439);
        endOfLetter.put('S',449);

        startOfLetter.put('T',449);
        endOfLetter.put('T',460);

        startOfLetter.put('U',461);
        endOfLetter.put('U',472);

        startOfLetter.put('V',473);
        endOfLetter.put('V',484);

        startOfLetter.put('W',485);
        endOfLetter.put('W',498);

        startOfLetter.put('X',499);
        endOfLetter.put('X',510);

        startOfLetter.put('Y',511);
        endOfLetter.put('Y',522);

        startOfLetter.put('Z',523);
        endOfLetter.put('Z',534);

        startOfLetter.put('1',535);
        endOfLetter.put('1',542);

        startOfLetter.put('2',543);
        endOfLetter.put('2',553);

        startOfLetter.put('3',554);
        endOfLetter.put('3',564);

        startOfLetter.put('4',565);
        endOfLetter.put('4',575);

        startOfLetter.put('5',576);
        endOfLetter.put('5',586);

        startOfLetter.put('6',587);
        endOfLetter.put('6',597);

        startOfLetter.put('7',598);
        endOfLetter.put('7',607);

        startOfLetter.put('8',608);
        endOfLetter.put('8',618);

        startOfLetter.put('9',619);
        endOfLetter.put('9',629);

        startOfLetter.put('0',630);
        endOfLetter.put('0',640);

    }
    public char getLetterL1(int index){
        char[] char1 = line1.toCharArray();
        return char1[index];
    }
    public char getLetterL2(int index){
        char[] char1 = line2.toCharArray();
        return char1[index];
    }
    public char getLetterL3(int index){
        char[] char1 = line3.toCharArray();
        return char1[index];
    }
    public char getLetterL4(int index){
        char[] char1 = line4.toCharArray();
        return char1[index];
    }
    public char getLetterL5(int index){
        char[] char1 = line5.toCharArray();
        return char1[index];
    }
    public char getLetterL6(int index){
        char[] char1 = line6.toCharArray();
        return char1[index];
    }
    public char getLetterL7(int index){
        char[] char1 = line7.toCharArray();
        return char1[index];
    }
    public char getLetterL8(int index){
        char[] char1 = line8.toCharArray();
        return char1[index];
    }
    public char getLetterL9(int index){
        char[] char1 = line9.toCharArray();
        return char1[index];
    }
    public char getLetterL10(int index){
        char[] char1 = line10.toCharArray();
        return char1[index];
    }
    public char getLetterL11(int index){
        char[] char1 = line11.toCharArray();
        return char1[index];
    }
}
