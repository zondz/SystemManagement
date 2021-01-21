import java.io.Serializable;
import java.util.ArrayList;

public class Student{
	private int id;
	private String name;
	private String className;
	
	public Student() {
		
	}
	
	public int getId() {
		return id;
	}

	public boolean setId(int id, ArrayList<Integer> usedId) throws WrongInputValueException  {
		if(validateId(usedId)) {
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
	
	private boolean validateId(ArrayList<Integer> usedId ) {
		boolean check =true;
		for(int i=0;i<usedId.size();i++) {
			if(usedId.get(i)==this.id) {
				return check=false;
			}
		}
		return check;
	}
}
