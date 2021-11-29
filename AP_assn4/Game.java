import java.io.*;
import java.util.*;

class Tile {
    int number;
    Toy toy;
    Tile(int n, String t) {
        number=n;
        toy=new Toy(t);
    }
    public Toy gettoy() {
        return this.toy;
    }
    public Toy getclone() {
        return this.toy.clone();
    }
}

class Carpet{
    private ArrayList<Tile> tiles;
    Carpet() {
        tiles=new ArrayList<Tile>();
    }
    public ArrayList<Tile> gettiles() {
        return tiles;
    }
    public void addtile(Tile t) {
        tiles.add(t);
    }
    public String gettoy(int n) {
        return tiles.get(n).gettoy().getname();
    }
}

class Calculator <T1,T2,T3> {
    private T1 d;
    private T2 e;
    public boolean check(T1 a,T2 b, T3 c) {
        if(a instanceof Integer && b instanceof Integer && c instanceof Integer) {
            int d=(int)a;
            int e=(int)b;
            if((d/e)==(int)c) return true;
            else return false;
        }
        else if(a instanceof String && b instanceof String && c instanceof String) {
            String d=(String)a;
            String e=(String)b;
            if(d.concat(e).equals((String)c)) return true;
            else return false;
        }
        return false;
    }
}

class Bucket {
    private ArrayList<Toy> toys;
    Bucket() {
        toys=new ArrayList<Toy>();
    }
    public void addtoy(Toy t) {
        toys.add(t);
    }
    public void printbucket() {
        int n=0;
        for(Toy t:toys) {
            n++;
            if(n==toys.size()){
                System.out.print(t.getname());
            }
            else {
                System.out.print(t.getname()+", ");
            }
        }
    }
}

class Toy implements Cloneable{
    private String name;
    Toy (String t) {
        name=t;
    }
    public String getname() {
        return this.name;
    }
    public Toy clone() {
        try {
            Toy toy=(Toy)super.clone();
            return toy;
        }
        catch(CloneNotSupportedException e) {
            return null;
        }
    }
}

class Player {
    private Bucket B;
    Player() {
        B=new Bucket();
    }
    public Bucket getbucket() {
        return B;
    }
}

public class Game {
    public static void main(String[] args) throws IOException {
        Reader.init(System.in);
        Player P=new Player();
        Carpet C=new Carpet();
        Calculator<String,String,String> cal1=new Calculator<String,String,String>();
        Calculator<Integer,Integer,Integer> cal2=new Calculator<Integer,Integer,Integer>();
        C.addtile(new Tile(1,"Teddy Bear"));
        C.addtile(new Tile(2,"Donald Duck"));
        C.addtile(new Tile(3,"Dog"));
        C.addtile(new Tile(4,"Mickey Mouse"));
        C.addtile(new Tile(5,"Jerry"));
        C.addtile(new Tile(6,"Tom"));
        C.addtile(new Tile(7,"Goofy"));
        C.addtile(new Tile(8,"Daisy"));
        C.addtile(new Tile(9,"Oggy"));
        C.addtile(new Tile(10,"Doraemon"));
        C.addtile(new Tile(11,"Shinchan"));
        C.addtile(new Tile(12,"Ninja Hattori"));
        C.addtile(new Tile(13,"Pikachu"));
        C.addtile(new Tile(14,"Winnie The Pooh"));
        C.addtile(new Tile(15,"Chhota Bheem"));
        C.addtile(new Tile(16,"Keymon Ache"));
        C.addtile(new Tile(17,"Tweety"));
        C.addtile(new Tile(18,"Dora"));
        C.addtile(new Tile(19,"Stuart Little"));
        C.addtile(new Tile(20,"Minion"));

        System.out.print("Hit enter to start the game!");
        Reader.nextLine();
        System.out.println("Game is ready!");
        int jump=0;
        int hop=0;
        while(jump<5) {
            System.out.print("Hit enter for your hop!");
            Reader.nextLine();
            jump++;
            hop=(int)(Math.random()*21)+1;
            if(hop>20) {
                System.out.println("You are too energetic and zoomed past all the tiles. Muddy Puddle Splash!");
            }
            else {
                System.out.println("You landed on tile "+hop);
                if(hop%2==0) {
                    System.out.println("You won a "+C.gettoy(hop-1)+" soft toy");
                    P.getbucket().addtoy(C.gettiles().get(hop-1).getclone());
                }
                else {
                    Random r=new Random();
                    while(true) {
                        System.out.println("Question answer round. integer or string?");
                        String choice=Reader.nextLine();
                        if(choice.equals("integer")) {
                            int a=r.nextInt(2000)+1;
                            int b=r.nextInt(2000)+1;
                            while(true) {
                                try {
                                    System.out.println("Calculate the result of "+a+" divided by "+b);
                                    int ans=Reader.nextInt();
                                    if(cal2.check(a,b,ans)==true) {
                                        System.out.println("Correct Answer!");
                                        System.out.println("You won a "+C.gettoy(hop-1)+" soft toy");
                                        P.getbucket().addtoy(C.gettiles().get(hop-1).getclone());
                                    }
                                    else {
                                        System.out.println("Incorrect Answer!");
                                        System.out.println("You did not win any soft toy!");
                                    }
                                    break;
                                }
                                catch (NumberFormatException e){
                                    System.out.println("Invalid! Answer can only be an integer! Try again!");
                                }
                            }
                            break;
                        }
                        else if(choice.equals("string")) {
                            StringBuilder a=new StringBuilder();
                            StringBuilder b=new StringBuilder();
                            String alphabet="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
                            for(int i=0;i<4;i++) {
                                int ind1=r.nextInt(alphabet.length());
                                int ind2=r.nextInt(alphabet.length());
                                a.append(alphabet.charAt(ind1));
                                b.append(alphabet.charAt(ind2));
                            }
                            System.out.println("Calculate the concatenation of the strings "+a+" and "+b);
                            String ans=Reader.nextLine();
                            if(cal1.check(a.toString(),b.toString(),ans)==true) {
                                System.out.println("Correct Answer!");
                                System.out.println("You won a "+C.gettoy(hop-1)+" soft toy");
                                P.getbucket().addtoy(C.gettiles().get(hop-1).getclone());
                            }
                            else {
                                System.out.println("Incorrect Answer!");
                                System.out.println("You did not win any soft toy!");
                            }
                            break;
                        }
                        else {
                            System.out.println("Invalid option! Try again!");
                        }
                    }
                }
            }
        }
        System.out.println("Game Over!");
        System.out.println("Soft toys won by you are:");
        P.getbucket().printbucket();
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
