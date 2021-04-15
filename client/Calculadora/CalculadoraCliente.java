import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.TException;
import java.util.Scanner;

public class CalculadoraCliente{
    public static void main(String[] args) throws TException{
        TSocket transporte = new TSocket("localhost", 9090);
        TBinaryProtocol protocolo = new TBinaryProtocol(transporte);
        CalculadoraServicio.Client cliente = new CalculadoraServicio.Client(protocolo);
        
        Scanner escaner = new Scanner(System.in);
        char hacerDeNuevo = ' ';
        int accionInvalida = 0;
		int operacion = 0;
		int num1, num2;

        do{
        	do{
        		accionInvalida = 0;
        		System.out.print("Introduzca el primer numero: ");
		        num1 = escaner.nextInt();

		        System.out.print("Introduzca el segundo numero: ");
		        num2 = escaner.nextInt();

		        System.out.println("Seleccione la operación que desea realizar: ");
		        System.out.println("1. Sumar");
		        System.out.println("2. Restar");
		        System.out.println("3. Multiplicar");
		        System.out.println("4. Dividir");
		        operacion = escaner.nextInt();

        		if(operacion > 4 || operacion < 0){
        			System.out.println("Opcion fuera de rango, intente de nuevo.");
        			accionInvalida = 1;
        		}else{
        			if(num2 == 0 && operacion == 4){
            			System.out.println("No se puede dividir entre 0.");
            			accionInvalida = 1;
                    }
        		}     		
        	}while(accionInvalida == 1);
        	        
	        transporte.open();
	        int result;
	        switch(operacion){
	        	case 1:
	        		result = cliente.suma(num1, num2);
        		break;
        		case 2:
	        		result = cliente.resta(num1, num2);
        		break;
        		case 3:
	        		result = cliente.multiplicacion(num1, num2);
        		break;
        		case 4:
	        		result = cliente.division(num1, num2);
        		break;
	        }
	        System.out.println("[Cliente] Resultado: " + result);
	        transporte.close();
	    }while(hacerDeNuevo != 'n' || hacerDeNuevo != 'N');
    }
}