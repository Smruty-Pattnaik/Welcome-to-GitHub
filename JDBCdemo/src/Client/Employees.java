package Client;
import Service.ServiceIMPL;

public class Employees {
	public static void main(String[]args) throws Exception{
		ServiceIMPL obj=new ServiceIMPL();
		
		obj.dbConnection();
		obj.show();
		obj.updateSalary();
		
		
	}

}
