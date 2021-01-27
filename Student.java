import java.io.Serializable;
import java.util.ArrayList;

public class Student implements Serializable{
	// 1801040203
	private String id;
	// Pham Tien Thanh
	private String name;
	private String className;
	
	public Student() {
		
	}
	public Student(String id,String name) throws WrongInputValueException {
		if(validateId(id)) {
			this.id=id;
		}
		else {
			throw new WrongInputValueException("Khởi tạo id học sinh sai: "+id);
		}
		if(validateName(name)) {
			this.name=name;
		}
		else {
			
			throw new WrongInputValueException("Khởi tạo tên học sinh sai: "+name);
		}
		
	}
	
	
	public String getId() {
		return this.id;
	}

	public boolean setId(String id) throws WrongInputValueException  {
		if(validateId(id)) {
		this.id = id;
		return true;
	}
		else {
		throw new WrongInputValueException("id cho student không hợp lệ :" +id);
		
	}
	}

	public String getName() {
		return name;
	}

	public boolean setName(String name) {
		if(validateName(name)) {
		this.name = name;
		return true;
		}
		return false;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}
	
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", className=" + className + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null) {
			return false;
		}
		if(obj instanceof Student) {
			if(((Student) obj).getId()== this.getId()) return true;
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}
	// id helper 
	private boolean validateId(String id) {
	if(id==null||id.length()!=10) {
		return false;
	}
		return true;
	}
	// name helper
	private boolean validateName(String name) {
		if(name==null||name.length()<0) {
			return false;
		}
		return true;
	}
	
	
}
