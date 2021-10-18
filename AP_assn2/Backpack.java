import java.io.*;
import java.util.*;

interface User{
    void print_menu();
    String getname();
}

interface Assessment {
    String getstatus();
    void setstatus(String s);
    String gettype();
    void setsub(Submission S);
    String getprob();
    Submission getsub();
    int getmarks();
}

class Submission {
    private ArrayList<String> names;
    private ArrayList<Integer> grades;
    private ArrayList<Student> students;
    private ArrayList<Instructor> instructors;
    Submission() {
        names=new ArrayList<>();
        grades=new ArrayList<>();
        students=new ArrayList<>();
        instructors=new ArrayList<>();
    }
    public void add_submission(String filename, Student stud) {
        names.add(filename);
        grades.add(-1);
        students.add(stud);
        instructors.add(null);
    }
    public boolean submitted(Student S) {
        for(Student y: this.students) {
            if(y==S) return true;
        }
        return false;
    }
    public ArrayList<Student> getstudents() {return this.students;}
    public ArrayList<Integer> getgrade() {return this.grades;}
    public int graded(Student S) {
        int t=0;
        for(Student y: this.students) {
            if(y==S) {return this.grades.get(t);}
        }
        return -1;
    }
    public String name(Student S) {
        int t=0;
        for(Student y: this.students) {
            if(y==S) {return this.names.get(t);}
        }
        return null;
    }
    public String ins_name(Student S) {
        int t=0;
        for(Student y: this.students) {
            if(y==S) {return this.instructors.get(t).getname();}
        }
        return null;
    }
    public ArrayList<String> getnames() {return this.names;}
    public ArrayList<Instructor> getinstructors() {return this.instructors;}
    public String get_stud_sub(int id) {return this.names.get(id);}
    public void setgrades(ArrayList<Integer> g) {this.grades=g;}
    public void setinstructor(ArrayList<Instructor> ins) {this.instructors=ins;}
    public boolean ungraded() {
        for(Integer y: this.grades) {
            if(y==-1) return true;
        }
        return false;
    }
}

class Comment {
    private static ArrayList<Comment> comments=new ArrayList<>();
    private User U;
    private String comment;
    private Date date;
    public void add_comment(String c, User u) {
        comments.add(this);
        U=u;
        comment=c;
        date=java.util.Calendar.getInstance().getTime();
    }
    public static void print_comments() {
        for(Comment y: Comment.comments) {
            System.out.println(y.comment+" -"+y.U.getname());
            System.out.println(y.date);
            System.out.println();
        }
    }
}

class Assignment implements Assessment{
    private static ArrayList<Assignment> assignments=new ArrayList<>();
    private String problem;
    private int marks;
    private Instructor instructor;
    private String status;
    private Submission S;
    public void add_assignment (String p,int m,Instructor I) {
        assignments.add(this);
        problem=p;
        marks=m;
        instructor=I;
        status="open";
        S=new Submission();
    }
    public static void view(int k) {
        for(Assignment y: Assignment.assignments) {
            System.out.println("ID: "+k+" Assignment: "+y.problem+" Max Marks: "+y.marks);
            System.out.println();
            k++;
        }
        System.out.println("---------");
    }

    @Override
    public String getstatus() {return this.status;}
    @Override
    public void setstatus(String s) {this.status=s;}
    @Override
    public String gettype() {return "assignment";}
    @Override
    public void setsub(Submission sub) {this.S=sub;}
    @Override
    public String getprob() {return this.problem;}
    @Override
    public Submission getsub() {return this.S;}
    @Override
    public int getmarks() {return this.marks;}
    static int getnumberofass() {return assignments.size();}
    public static ArrayList<Assignment> getassignments() {return assignments;}
}

class Quiz implements Assessment{
    private static ArrayList<Quiz> quizzes=new ArrayList<>();
    private String ques;
    private int marks;
    private Instructor instructor;
    private String status;
    private Submission S;
    public void add_quiz (String q,Instructor I) {
        quizzes.add(this);
        ques=q;
        marks=1;
        status="open";
        S=new Submission();
    }
    public static void view(int k) {
        for(Quiz y: Quiz.quizzes) {
            System.out.println("ID: "+k+" Assignment: "+y.ques);
            System.out.println();
            k++;
        }
        System.out.println("---------");
    }
    @Override
    public String getstatus() {return this.status;}
    @Override
    public void setstatus(String s) {this.status=s;}
    @Override
    public String gettype() {return "quiz";}
    @Override
    public void setsub(Submission sub) {this.S=sub;}
    @Override
    public String getprob() {return this.ques;}
    @Override
    public Submission getsub() {return this.S;}
    @Override
    public int getmarks() {return this.marks;}
    public static ArrayList<Quiz> getquizzes() {return quizzes;}
    public String getques() {return this.ques;}
}

class LectureSlide {
    private static ArrayList<LectureSlide> slides =new ArrayList<>();
    private String topic;
    private int number;
    private String[] content;
    private Instructor instructor;
    private Date dou;
    public void add_slide(String t,int n, String[] c,Instructor I) {
        slides.add(this);
        topic=t;
        number=n;
        content=c;
        instructor=I;
        dou=java.util.Calendar.getInstance().getTime();
    }
    public static void view() {
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
    public static int getlecsize() {return slides.size();}
}

class LectureVideo {
    private static ArrayList<LectureVideo> videos =new ArrayList<>();
    private String topic;
    private String filename;
    private Date dou;
    private Instructor instructor;
    public void add_video(String t,String f,Instructor I) {
        videos.add(this);
        topic=t;
        filename=f;
        dou=java.util.Calendar.getInstance().getTime();
        instructor=I;
    }
    public static void view() {
        for(LectureVideo y:LectureVideo.videos) {
            System.out.println("Title of video: "+y.topic);
            System.out.println("Video File: "+y.filename);
            System.out.println("Date of Upload: "+y.dou);
            System.out.println("Uploaded by: "+y.instructor.getname());
            System.out.println();
        }
    }
    public static int getlecsize() {return videos.size();}
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
                    System.out.println(k+" "+y.getname());
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
                        if(LectureSlide.getlecsize()==0 && LectureVideo.getlecsize()==0) System.out.println("No material available!");
                        else {
                            LectureSlide.view();
                            LectureVideo.view();
                        }
                    }
                    else if(p==4) {
                        Assignment.view(0);
                        Quiz.view(Assignment.getnumberofass());
                    }
                    else if(p==5) {
                        System.out.println("List of assessments");
                        int z=0;
                        ArrayList<Assessment> ungraded_assessments=new ArrayList<>();
                        for(Assignment y:Assignment.getassignments()) {
                            if(y.getsub().getstudents().size()!=0 && y.getsub().ungraded()==true) {
                                ungraded_assessments.add(y);
                                System.out.println("ID "+z+" Assignment: "+y.getprob()+" Max Marks: "+y.getmarks());
                                System.out.println("-----------");
                                z++;
                            }
                        }
                        for(Quiz y:Quiz.getquizzes()) {
                            if(y.getsub().getstudents().size()!=0 && y.getsub().ungraded()==true) {
                                ungraded_assessments.add(y);
                                System.out.println("ID "+z+" Assignment: "+y.getques());
                                System.out.println("-----------");
                                z++;
                            }
                        }
                        if(ungraded_assessments.size()==0) System.out.println("No ungraded assessments!");
                        else {
                            System.out.print("Enter id of assessment to view submissions: ");
                            int aid=Reader.nextInt();
                            ArrayList<Student> studs=ungraded_assessments.get(aid).getsub().getstudents();
                            ArrayList<Integer> grades=ungraded_assessments.get(aid).getsub().getgrade();
                            ArrayList<String> names=ungraded_assessments.get(aid).getsub().getnames();
                            ArrayList<Instructor> ins=ungraded_assessments.get(aid).getsub().getinstructors();
                            int h=0;
                            for(Student y: studs) {
                                if(grades.get(h)==-1) System.out.println(h+". "+y.getname());
                                h++;
                            }
                            int l=Reader.nextInt();
                            System.out.println("Submission: "+names.get(l));
                            System.out.println("--------------");
                            System.out.println("Max Marks: "+ungraded_assessments.get(aid).getmarks());
                            System.out.print("Marks Scored: ");
                            int mar=Reader.nextInt();
                            grades.set(l, mar);
                            ins.set(l,instructors.get(i));
                            ungraded_assessments.get(aid).getsub().setgrades(grades);
                            ungraded_assessments.get(aid).getsub().setinstructor(ins);
                        }
                    }
                    else if(p==6) {
                        int z=0;
                        ArrayList<Assessment> open_assessments=new ArrayList<>();
                        System.out.println("List of Open Assignments:");
                        for(Assignment y:Assignment.getassignments()) {
                            if(y.getstatus().equals("open")) {
                                open_assessments.add(y);
                                System.out.println("ID "+z+" Assignment: "+y.getprob()+" Max Marks: "+y.getmarks());
                                System.out.println("-----------");
                                z++;
                            }
                        }
                        for(Quiz y:Quiz.getquizzes()) {
                            if(y.getstatus().equals("open")) {
                                open_assessments.add(y);
                                System.out.println("ID "+z+" Assignment: "+y.getques());
                                System.out.println("-----------");
                                z++;
                            }
                        }
                        if(open_assessments.size()==0) System.out.println("No open assessments!");
                        else {
                            System.out.println("Enter id of assignment to close: ");
                            int id=Reader.nextInt();
                            open_assessments.get(id).setstatus("closed");
                        }
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
                    else if(p!=9) System.out.println("Invalid option entered!");
                }while(p!=9);
            }
            else if(o==2) {
                int k=0;
                System.out.println("Students:");
                for(Student y:students) {
                    System.out.println(k+" "+y.getname());
                    k++;
                }
                System.out.print("Choose id:");
                int i=Reader.nextInt();
                int p;
                do{
                    students.get(i).print_menu();
                    p=Reader.nextInt();
                    if(p==1) {
                        if(LectureSlide.getlecsize()==0 && LectureVideo.getlecsize()==0) System.out.println("No material available!");
                        else {
                            LectureSlide.view();
                            LectureVideo.view();
                        }
                    }
                    else if(p==2) {
                        Assignment.view(0);
                        Quiz.view(Assignment.getnumberofass());
                    }
                    else if(p==3) {
                        System.out.println("Pending Assessments");
                        int z=0;
                        ArrayList<Assessment> pending_assessments=new ArrayList<>();
                        for(Assignment y:Assignment.getassignments()) {
                            if(y.getstatus().equals("open") && (y.getsub()==null || y.getsub().submitted(students.get(i))==false)) {
                                pending_assessments.add(y);
                                System.out.println("ID "+z+" Assignment: "+y.getprob()+" Max Marks: "+y.getmarks());
                                z++;
                            }
                        }
                        for(Quiz y:Quiz.getquizzes()) {
                            if(y.getstatus().equals("open") && (y.getsub()==null || y.getsub().submitted(students.get(i))==false)) {
                                pending_assessments.add(y);
                                System.out.println("ID "+z+" Assignment: "+y.getques());
                                z++;
                            }
                        }
                        if(pending_assessments.size()==0) System.out.println("no pending assessments!");
                        else {
                            System.out.print("Enter ID of assessment: ");
                            int id=Reader.nextInt();
                            if(pending_assessments.get(id).gettype().equals("assignment")) {
                                System.out.print("Enter filename of assignment: ");
                                String n=Reader.nextLine();
                                if(n.substring(n.length()-4).equals(".zip")) {
                                    pending_assessments.get(id).getsub().add_submission(n, students.get(i));
                                }
                                else System.out.println("Invalid filename!");
                            }
                            else if(pending_assessments.get(id).gettype().equals("quiz")) {
                                System.out.print(pending_assessments.get(id).getprob()+" ");
                                String ans=Reader.nextLine();
                                pending_assessments.get(id).getsub().add_submission(ans, students.get(i));
                            }
                        }
                    }
                    else if(p==4) {
                        ArrayList<Assessment> graded_assessments=new ArrayList<>();
                        ArrayList<Assessment> ungraded_assessments=new ArrayList<>();
                        for(Assignment y: Assignment.getassignments()) {
                            for(Student l: y.getsub().getstudents()) {
                                if(students.get(i)==l && y.getsub().graded(students.get(i))!=-1) graded_assessments.add(y);
                                else if(students.get(i)==l && y.getsub().graded(students.get(i))==-1) ungraded_assessments.add(y);
                            }
                        }
                        for(Quiz y: Quiz.getquizzes()) {
                            for(Student l: y.getsub().getstudents()) {
                                if(students.get(i)==l && y.getsub().graded(students.get(i))!=-1) graded_assessments.add(y);
                                else if(students.get(i)==l && y.getsub().graded(students.get(i))==-1) ungraded_assessments.add(y);
                            }
                        }
                        System.out.println("Graded Submissions:");
                        for(Assessment y: graded_assessments) {
                            for(Student l: y.getsub().getstudents()) {
                                if(students.get(i)==l && y.getsub().graded(students.get(i))!=-1) {
                                    System.out.println("Submission: "+y.getsub().name(students.get(i)));
                                    System.out.println("Marks Scored: "+y.getsub().graded(students.get(i)));
                                    System.out.println("Graded by: "+y.getsub().ins_name(students.get(i)));
                                    System.out.println();
                                }
                            }
                        }
                        System.out.println("--------------------------");
                        System.out.println("Ungraded Submissions:");
                        for(Assessment y: ungraded_assessments) {
                            for(Student l: y.getsub().getstudents()) {
                                if(students.get(i)==l && y.getsub().graded(students.get(i))==-1) {
                                    System.out.println("Submission: "+y.getsub().name(students.get(i)));
                                    System.out.println();
                                }
                            }
                        }
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
                    else if(p!=7) System.out.println("Invalid option entered!");
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
