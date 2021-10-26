import java.io.*;
import java.util.*;
class Player {
    private String name;
    private int points;
    private int pos;
    Player(String n) {
        name=n;
        points=0;
        pos=0;
    }
    public int get_points() {return this.points;}
    public int get_pos() {return this.pos;}
    public String get_name() {return this.name;}
    public void set_pos(int p) {this.pos=p;}
    public void set_points(int p) {this.points=p;}
}
class Dice {
    private final int num_faces=2;
    public int num() {
        int n=(int)(Math.random()*num_faces)+1;
        return n;
    }
}
abstract class Floor {
    int points;
    Player P;
    abstract void reach_floor(Player _P);
}
class Empty_Floor extends Floor {
    Empty_Floor(){
        points=1;
        P=null;
    }
    @Override
    void reach_floor(Player _P) {
        P=_P;
        System.out.println(P.get_name()+" has reached an Empty Floor");
        P.set_points(this.points+P.get_points());
        System.out.println("Total Points "+P.get_points());
    }
}
class Snake_Floor extends Floor {
    private final int location=5;
    Snake_Floor() {
        points=-2;
        P=null;
    }
    @Override
    void reach_floor(Player _P) {
        P=_P;
        System.out.println("Player position Floor-"+location);
        System.out.println(P.get_name()+" has reached a Normal Snake Floor");
        P.set_points(points+P.get_points());
        System.out.println("Total Points "+P.get_points());
    }
}
class King_Cobra extends Floor {
    private final int location=11;
    King_Cobra() {
        points=-4;
        P=null;
    }
    @Override
    void reach_floor(Player _P) {
        P=_P;
        System.out.println("Player position Floor-"+location);
        System.out.println(P.get_name()+" has reached a King Cobra Floor");
        P.set_points(points+P.get_points());
        System.out.println("Total Points "+P.get_points());
    }
}
class Elevator extends Floor {
    private final int location=2;
    Elevator() {
        points=4;
        P=null;
    }
    @Override
    void reach_floor(Player _P) {
        P=_P;
        System.out.println("Player position Floor-"+location);
        System.out.println(P.get_name()+" has reached an Elevator Floor");
        P.set_points(points+P.get_points());
        System.out.println("Total Points "+P.get_points());
    }
}
class Ladder_Floor extends Floor {
    private final int location=8;
    Ladder_Floor() {
        points=2;
        P=null;
    }
    @Override
    void reach_floor(Player _P) {
        P=_P;
        System.out.println("Player position Floor-"+location);
        System.out.println(P.get_name()+" has reached a Ladder Floor");
        P.set_points(points+P.get_points());
        System.out.println("Total Points "+P.get_points());
    }
}

public class Game {
    public static void main(String[] args) throws IOException {
        Reader.init(System.in);
        System.out.println("Enter player name and hit enter");
        String name=Reader.nextLine();
        Player P=new Player(name);
        Dice D=new Dice();
        System.out.println("The game setup is ready");
        int dice;
        do {
            System.out.print("Hit enter to roll the dice");
            Reader.nextLine();
            dice=D.num();
            System.out.println("Dice gave "+dice);
            if(dice!=1) {
                System.out.println("Game cannot start until you get 1");
            }
            else {
                System.out.println("Player Position Floor-"+P.get_pos());
                Empty_Floor E=new Empty_Floor();
                E.reach_floor(P);
            }
        } while(dice!=1);
        while(P.get_pos()<=13) {
            System.out.print("Hit enter to roll the dice");
            Reader.nextLine();
            dice=D.num();
            System.out.println("Dice gave "+dice);
            P.set_pos(dice+P.get_pos());
            if(P.get_pos()==2) {
                Elevator EL=new Elevator();
                EL.reach_floor(P);
                P.set_pos(10);
                System.out.println("Player Position Floor-"+P.get_pos());
                Empty_Floor E=new Empty_Floor();
                E.reach_floor(P);
            }
            else if(P.get_pos()==5) {
                Snake_Floor S=new Snake_Floor();
                S.reach_floor(P);
                P.set_pos(1);
                System.out.println("Player Position Floor-"+P.get_pos());
                Empty_Floor E=new Empty_Floor();
                E.reach_floor(P);
            }
            else if(P.get_pos()==8) {
                Ladder_Floor L=new Ladder_Floor();
                L.reach_floor(P);
                P.set_pos(12);
                System.out.println("Player Position Floor-"+P.get_pos());
                Empty_Floor E=new Empty_Floor();
                E.reach_floor(P);
            }
            else if(P.get_pos()==11) {
                King_Cobra K=new King_Cobra();
                K.reach_floor(P);
                P.set_pos(3);
                System.out.println("Player Position Floor-"+P.get_pos());
                Empty_Floor E=new Empty_Floor();
                E.reach_floor(P);
            }
            else if(P.get_pos()<=11) {
                System.out.println("Player Position Floor-"+P.get_pos());
                Empty_Floor E=new Empty_Floor();
                E.reach_floor(P);
            }
            else if(P.get_pos()==12) {
                System.out.println("Player Position Floor-"+P.get_pos());
                Empty_Floor E=new Empty_Floor();
                E.reach_floor(P);
                do {
                    System.out.print("Hit enter to roll the dice");
                    Reader.nextLine();
                    dice=D.num();
                    System.out.println("Dice gave "+dice);
                    if(dice!=1) {
                        System.out.println("Player cannot move");
                    }
                    else {
                        System.out.println("Player Position Floor-"+P.get_pos());
                        E.reach_floor(P);
                        break;
                    }
                } while(dice!=1);
                break;
            }
            else if(P.get_pos()==13) {
                System.out.println("Player Position Floor-"+P.get_pos());
                Empty_Floor E=new Empty_Floor();
                E.reach_floor(P);
                break;
            }
        }
        System.out.println("Game Over");
        System.out.println(P.get_name()+" accumulated "+P.get_points()+" points");
    }
}

class Reader {
    static BufferedReader reader;
    static StringTokenizer tokenizer;

    /** call this method to initialize reader for InputStream */
    static void init(InputStream input) {
        reader = new BufferedReader(
                     new InputStreamReader(input) );
        tokenizer = new StringTokenizer("");
    }

    /** get next word */
    static String next() throws IOException {
        while ( ! tokenizer.hasMoreTokens() ) {
            //TODO add check for eof if necessary
            tokenizer = new StringTokenizer(
                   reader.readLine() );
        }
        return tokenizer.nextToken();
    }
    static String nextLine() throws IOException {
    return reader.readLine();
    }

    static int nextInt() throws IOException {
        return Integer.parseInt( next() );
    }
	
    static double nextDouble() throws IOException {
        return Double.parseDouble( next() );
    }
}
