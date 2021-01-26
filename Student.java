import java.io.Serializable;
import java.util.ArrayList;

public class Student implements Serializable{
	private int id;
	private String name;
	private String className;
	
	public Student() {
		
	}
	public Student(int id,String name) {
		this.id=id;
		this.name=name;
	}
	
	
	public int getId() {
		return id;
	}

	public boolean setId(int id) throws WrongInputValueException  {
		if(validateId(id)) {
		this.id = id;
		return true;
	}
		else {
		throw new WrongInputValueException("Wrong id:" +id);
		
	}
	}

	public String getName() {
		return name;
	}

	public boolean setName(String name) {
		this.name = name;
		return true;
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
	
	private boolean validateId(int id) {
		boolean check =true;
//		for(int i=0;i<usedId.size();i++) {
//			if(usedId.get(i)==this.id) {
//				return check=false;
//			}
//		}
		return check;
	}
}
