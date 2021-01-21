import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


/**
 * Bài tập
 * Chương trình in ra console
 * Chức năng chính :
 * 1. Hiển thị danh sách sinh viên
 * 2. Hiển thị danh sách lớp học
 * 3. Quản lý sinh viên
 * 3.1.1 Thêm sinh viên
 * 3.1.2 Sửa sinh viên
 * 3.1.3 Xóa sinh viên
 * 3.2 Thêm sinh viên vào trong lớp học
 * 3.3 Xóa sinh viên khỏi lớp học
 * 4. Quản lý lớp học
 * 4.1.1 Thêm lớp học 
 * 4.1.2 Sửa lớp học
 * 4.1.3 Xóa lớp học
 * 4.2 Chọn ra lớp trưởng của lớp học
 * 
 * Yêu cầu : ghi thông tin sinh viên , lớp học ra file
 * + file sinh viên gồm : id sinh viên, tên sinh viên , mã lớp 
 * + file lớp học gồm :id lớp học , tên lớp học , mã sinh viên của lớp trưởng
 * 
 * notes : id của file phải là duy nhất
 * @author Thanh
 *
 */
public class Main {
	
private static ArrayList<Classroom> classroomList = new ArrayList<Classroom>(); 
//private static ArrayList<String> fileNameList = new ArrayList<String>();
private static ArrayList<Integer> usedId= new ArrayList<Integer>();

	/**
	 * Thực hiện show menu , các chức năng của chương trình
	 * Gọi các function tương ứng
	 * @param args
	 */
	public static void main(String[]args) {
try {
	Classroom cls = new Classroom();
			createNewStudent();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WrongInputValueException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

/**
 *  1. Hiển thị danh sách sinh viên
 */
public static ArrayList<Student> showStudents(ArrayList<Classroom> classroomList) {
	// kiểm tra có sinh viên hay không
	if(classroomList.size()==0) {
		return null; 
	}
	ArrayList<Student> allReturnedStudent = null;
	ArrayList<Student> getStudentReturnListFromEachClass;
	for(int i=0;i<classroomList.size();i++) {
		if(classroomList.get(i).NumberOfStudent()==0) {
			continue;
		}
		allReturnedStudent = new ArrayList<Student>();
		getStudentReturnListFromEachClass = classroomList.get(i).getStudentList();
		
		for(int j=0;j<getStudentReturnListFromEachClass.size();j++) {
			allReturnedStudent.add(getStudentReturnListFromEachClass.get(j));
//			System.out.println(getStudentReturnListFromEachClass.get(j).toString());
		}
	}
	return allReturnedStudent;
	
	
}
/**
 *  2. Hiển thị danh sách lớp học
 */
public static void showClassList(ArrayList<Classroom> classroomList) {
 if(classroomList.size()==0) {
	 System.out.println("Không có lớp nào ");
	 return;
 }
	for(Classroom cl : classroomList) {
		System.out.println("class name: "+ cl.getClassroomName());
	}
	
}

//public static boolean checkIfExistStudent(ArrayList<Classroom> classroomList) {
//	if(classroomList.size()==0) return false;
//	boolean check=false;
//	for(Classroom cl : classroomList) {
//		if(cl.getStudentList().size()!=0) {
//			check=true;
//		}
//	}
//	return check;
//}


/**
 * 3. Quản lý sinh viên
 * 
 */
public static void studentManagement() {
	
}

/**
 * 3.1.1 Thêm sinh viên 
 * @throws WrongInputValueException 
 * @throws IOException 
 * @throws Exception 
 */
public static Student createNewStudent() throws WrongInputValueException, IOException  {
	ArrayList<Student> allReturnedStudent=showStudents(classroomList);
	Scanner sc = new Scanner(System.in);
	System.out.println("Insert Student id: ");
	int id = sc.nextInt();
	sc.nextLine();
	System.out.println("Insert Student name: ");
	String name = sc.nextLine();
//	System.out.println("Insert Student classroom: ");
//	String className = sc.nextLine();
//	
	Student newStudent = new Student();
	newStudent.setId(id, usedId);
	newStudent.setName(name) ;
	

	
	//String.valueOf(newStudent.getId())+newStudent.getName()+String.valueOf(getIdOfClass(newStudent.getId(), classroomList));
	//getIdOfClass(newStudent.getId())!=-1
	if(getIdOfClass(newStudent.getId())!=-1) {
	String[] saveInfor = {String.valueOf(newStudent.getId()),newStudent.getName(),String.valueOf(getIdOfClass(newStudent.getId()))};
	String fileName = "Student-"+newStudent.getId()+".txt";
	File file = new File(fileName);
	System.out.println(file.getAbsolutePath());
	FileWriter fr=null;
	try{file.createNewFile();
	fr = new FileWriter(file);
	for(int i=0;i<3;i++) {
	fr.write("Student id:" +saveInfor[i]+ "\n");	
	}
	}catch(IOException e) {
		throw e;
	}
	finally {
		fr.close();
	
	}
	}
	// code  mẫu IO file trên youtube
//	ObjectOutputStream oos=new ObjectOutputStream(fos);
//	oos.writeObject(newStudent);
//	fos.close();
//	oos.close();
	return newStudent;
}

public static int getIdOfClass (int studentId) {
	ArrayList<Student> allReturnedStudent = showStudents(classroomList);
	int id =-1;
	if(allReturnedStudent!=null) {
	for(Student std : allReturnedStudent) {
		if(std.getId()==studentId) {
			
			String className = std.getClassName();
			
			for(Classroom cls : classroomList) {
				if(cls.getClassroomName()==className) {
					id= cls.getId();
					return id;
				}
			}
			
		}
	}
	}
	return id;
}

/**
 * 3.1.2 Sửa sinh viên
 */
public static void modifySudent(int id,ArrayList<Classroom> classroomList) {
	
	
	//find all student exist
	ArrayList<Student> allReturnedStudent = showStudents(classroomList);	
//	if(allReturnedStudent==null){
//		return;
//	}
//	// modify that studentInfo with that id
//	for(Student std : allReturnedStudent) {
//		if(std.getId()==id) {
//			Scanner sc = new Scanner(System.in);
//			System.out.println("Insert new id: ");
//			std.setId(sc.nextInt());
//			sc.nextLine();
//					
//		}
//		
//	}
	// find that existing file and update
	
}
/**
 * 3.1.3 Xóa sinh viên
 */
public static void deleteStudent() {}

/**
 * 3.2 Thêm student vào class
 */
public static void addStudentToClass() {
	
}
/**
 * 3.3 Xóa student khỏi class
 */
public static void deleteStudentFromClass() {
	
}
/**
 * 4. Quản lý lớp học
 * 
 */
public void manageClasses() {
	
}
/**
 * 4.1.1 Thêm lớp học mới
 */
 public static void createNewClass() {
	 
 }
 /**
  * 4.1.2 Sửa lớp học
  */
 public static void modifyClass() {
	 
 }
 /**
  * 4.1.3 Xóa lớp học
  */
 public static void removeClass() {}
 /**
  * Chọn ra lớp trưởng lớp học
  */
 public static void selectMonitor() {}
 
}
