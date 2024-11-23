package practice;

public class Student {
private int id;
private String name;
private double percentage;
private long phone;
private String stream;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public double getPercentage() {
	return percentage;
}
public void setPercentage(double percentage) {
	this.percentage = percentage;
}
public long getPhone() {
	return phone;
}
public void setPhone(long phone) {
	this.phone = phone;
}
public String getStream() {
	return stream;
}
public Student(String name, double percentage, long phone, String stream) {
	super();
	this.name = name;
	this.percentage = percentage;
	this.phone = phone;
	this.stream = stream;
}
public void setStream(String stream) {
	this.stream = stream;
}
public Student(int id, String name, double percentage, long phone, String stream) {
	this(name,percentage,phone,stream);
	
	this.id = id;
	
}
@Override
public String toString() {
	return "Student [id=" + id + ", name=" + name + ", percentage=" + percentage + ", phone=" + phone + ", stream="
			+ stream + "]";
}

}
