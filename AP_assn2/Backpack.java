import java.io.*;
import java.util.*;

//import javax.xml.stream.events.Comment;

interface User{
    void print_menu();
    String getname();
}

class Comment {
    private static ArrayList<Comment> comments=new ArrayList<>();
    private User U;
    private String comment;
    private Date date;
    void add_comment(String c, User u) {
        comments.add(this);
        U=u;
        comment=c;
        date=java.util.Calendar.getInstance().getTime();
    }
    static void print_comments() {
        for(Comment y: Comment.comments) {
            System.out.println(y.comment+" -"+y.U.getname());
            System.out.println(y.date);
            System.out.println();
        }
    }
}

class Assignment {
    private static ArrayList<Assignment> assignments=new ArrayList<>();
    private String problem;
    private int marks;
    private Instructor instructor;
    private ArrayList<Boolean> graded=new ArrayList<>();
    void add_assignment (String p,int m,Instructor I) {
        assignments.add(this);
        problem=p;
        marks=m;
        instructor=I;
        ArrayList<Student> submissions=new ArrayList<>();
    }
    static void view(int k) {
        for(Assignment y: Assignment.assignments) {
            System.out.println("ID: "+k+" Assignment: "+y.problem+" Max Marks: "+y.marks);
            System.out.println();
            k++;
        }
        System.out.println("---------");
    }
    static int getnumberofass() {return assignments.size();}
}

class Quiz {
    private static ArrayList<Quiz> quizzes=new ArrayList<>();
    private String ques;
    private int marks;
    private Instructor instructor;
    private ArrayList<Boolean> graded=new ArrayList<>();
    void add_quiz (String q,Instructor I) {
        quizzes.add(this);
        ques=q;
        marks=1;
        ArrayList<Student> submissions=new ArrayList<>();
    }
    static void view(int k) {
        for(Quiz y: Quiz.quizzes) {
            System.out.println("ID: "+k+" Assignment: "+y.ques);
            System.out.println();
            k++;
        }
        System.out.println("---------");
    }
}

class LectureSlide {
    private static ArrayList<LectureSlide> slides =new ArrayList<>();
    private String topic;
    private int number;
    private String[] content;
    private Instructor instructor;
    private Date dou;
    void add_slide(String t,int n, String[] c,Instructor I) {
        slides.add(this);
        topic=t;
        number=n;
        content=c;
        instructor=I;
        dou=java.util.Calendar.getInstance().getTime();
    }
    static void view() {
        for(LectureSlide y:LectureSlide.slides) {
            System.out.println("Title: "+y.topic);
            for(int z=0;z<y.number;z++) {
                System.out.println("Slide "+(z+1)+": "+y.content[z]);
            }
            System.out.println("Number of slides: "+y.number);
            System.out.println("Date of Upload: "+y.dou);
            System.out.println("Uploaded by: "+y.instructor.getname());
            System.out.println();
        }
    }
}

class LectureVideo {
    private static ArrayList<LectureVideo> videos =new ArrayList<>();
    private String topic;
    private String filename;
    private Date dou;
    private Instructor instructor;
    void add_video(String t,String f,Instructor I) {
        videos.add(this);
        topic=t;
        filename=f;
        dou=java.util.Calendar.getInstance().getTime();
        instructor=I;
    }
    static void view() {
        for(LectureVideo y:LectureVideo.videos) {
            System.out.println("Title of video: "+y.topic);
            System.out.println("Video File: "+y.filename);
            System.out.println("Date of Upload: "+y.dou);
            System.out.println("Uploaded by: "+y.instructor.getname());
            System.out.println();
        }
    }
}

class Instructor implements User{
    private String name;
    
    Instructor(String n) {
        name=n;
    }

    @Override
    public void print_menu() {
        System.out.println("Welcome "+this.name);
        System.out.println("INSTRUCTOR MENU");
        System.out.println("1. Add class material");
        System.out.println("2. Add assessments");
        System.out.println("3. View lecture materials");
        System.out.println("4. View assessments");
        System.out.println("5. Grade assessments");
        System.out.println("6. Close assessment");
        System.out.println("7. View comments");
        System.out.println("8. Add comments");
        System.out.println("9. Logout");
    }

    @Override
    public String getname() {return this.name;}

}

class Student implements User {
    private String name;
    Student(String n) {
        name=n;
    }

    @Override
    public void print_menu() {
        System.out.println("Welcome "+this.name);
        System.out.println("STUDENT MENU");
        System.out.println("1. View lecture materials");
        System.out.println("2. View assessments");
        System.out.println("3. Submit assessment");
        System.out.println("4. View grades");
        System.out.println("5. View comments");
        System.out.println("6. Add comments");
        System.out.println("7. Logout");
    }

    @Override
    public String getname() {return this.name;}
}

public class Backpack{
    public static void main(String[] args) throws IOException {
        Reader.init(System.in);
        ArrayList<Instructor> instructors=new ArrayList<>();
        ArrayList<Student> students=new ArrayList<>();
        Student S0=new Student("S0");
        Student S1=new Student("S1");
        Student S2=new Student("S2");
        students.add(S0);
        students.add(S1);
        students.add(S2);
        Instructor I0=new Instructor("I0");
        Instructor I1=new Instructor("I1");
        instructors.add(I0);
        instructors.add(I1);
        
        int o=0;
        do {
            System.out.println("Welcome to Backpack!");
            System.out.println("1. Enter as instructor");
            System.out.println("2. Enter as student");
            System.out.println("3. Exit");
            
            o=Reader.nextInt();
            if(o==1) {
                int k=0;
                System.out.println("Instructors:");
                for(Instructor y:instructors) {
                    System.out.println(k+"- I"+k);
                    k++;
                }
                System.out.print("Choose id:");
                int i=Reader.nextInt();
                int p;
                do{
                    instructors.get(i).print_menu();
                    p=Reader.nextInt();
                    if(p==1) {
                        System.out.println("1. Add Lecture Slide");
                        System.out.println("2. Add Lecture Video");
                        int q=Reader.nextInt();
                        if(q==1) {
                            System.out.print("Enter topic of slides:");
                            String topic=Reader.nextLine();
                            System.out.print("Enter number of slides:");
                            int no=Reader.nextInt();
                            System.out.println("Enter content of slides:");
                            String[] content=new String[no];
                            for(int r=0;r<no;r++) {
                                System.out.print("Content of slide "+(r+1)+":");
                                content[r]=Reader.nextLine();
                            }
                            LectureSlide L=new LectureSlide();
                            L.add_slide(topic,no,content,instructors.get(i));
                        }
                        else if(q==2) {
                            System.out.print("Enter topic of video:");
                            String topic=Reader.nextLine();
                            System.out.print("Enter filname of video:");
                            String filename=Reader.nextLine();
                            if(filename.substring(filename.length()-4).equals(".mp4")) {
                                LectureVideo L=new LectureVideo();
                                L.add_video(topic,filename,instructors.get(i));
                            }
                            else System.out.println("Invalid filename!!");
                        }
                    }
                    else if(p==2) {
                        System.out.println("1. Add Assignment");
                        System.out.println("2. Add Quiz");
                        int q=Reader.nextInt();
                        if(q==1) {
                            System.out.print("Enter problem statement:");
                            String prob=Reader.nextLine();
                            System.out.print("Enter max marks:");
                            int marks=Reader.nextInt();
                            Assignment A=new Assignment();
                            A.add_assignment(prob, marks, instructors.get(i));
                        }
                        if(q==2) {
                            System.out.print("Enter quiz question: ");
                            String ques=Reader.nextLine();
                            Quiz Q=new Quiz();
                            Q.add_quiz(ques, instructors.get(i));
                        }
                    }
                    else if(p==3) {
                        LectureSlide.view();
                        LectureVideo.view();
                    }
                    else if(p==4) {
                        Assignment.view(0);
                        Quiz.view(Assignment.getnumberofass());
                    }
                    else if(p==7) {
                        Comment.print_comments();
                    }
                    else if(p==8) {
                        System.out.print("Enter comment: ");
                        String c=Reader.nextLine();
                        Comment C =new Comment();
                        C.add_comment(c, instructors.get(i));
                    }
                }while(p!=9);
            }
            else if(o==2) {
                int k=0;
                System.out.println("Students:");
                for(Student y:students) {
                    System.out.println(k+"- S"+k);
                    k++;
                }
                System.out.print("Choose id:");
                int i=Reader.nextInt();
                int p;
                do{
                    students.get(i).print_menu();
                    p=Reader.nextInt();
                    if(p==1) {
                        LectureSlide.view();
                        LectureVideo.view();
                    }
                    else if(p==2) {
                        Assignment.view(0);
                        Quiz.view(Assignment.getnumberofass());
                    }
                    else if(p==5) {
                        Comment.print_comments();
                    }
                    else if(p==6) {
                        System.out.print("Enter comment: ");
                        String c=Reader.nextLine();
                        Comment C =new Comment();
                        C.add_comment(c, students.get(i));
                    }
                }while(p!=7);
            }
            else if(o!=3) System.out.println("Invalid option entered!");
        } while(o!=3);
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
