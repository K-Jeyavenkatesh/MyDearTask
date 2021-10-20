import com.alert.AlertByThread;
import com.database.CurrentDataValidation;
import com.usermodule.Loginpage;

public class Driver {
	
	public static void main(String[] args) {
		
		new Loginpage();
		new CurrentDataValidation().CurrentDataValidation1();
		new CurrentDataValidation().CurrentDataValidation2();
		try {
			new AlertByThread();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
