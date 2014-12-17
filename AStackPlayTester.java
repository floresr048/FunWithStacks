import javax.swing.JOptionPane;

class AStackPlayTester
{
	public static void main(String[] arg)
	{
		String arithmevil[] = {"5 + ) * ( 2",
					  " 2 + { 2 * ( 10 - 4 ) / [ { 4 * 2 / ( 3 + 4 ) } + 2 ] - 9 }",
				      " 2 + ( - 3 * 5 ) ",
				      " 2 + } 2 * ( 10 - 4 ) / [ { 4 * 2 / ( 3 + 4) } + 2 ] - 9 { ",
		              "(( 2 + 3 ) * 5 ) * 8 ",
		              "5 * 10 + ( 15 - 20 ) ) - 25",
		              " 5 + ( 5 *  10 + ( 15 - 20 ) - 25 ) * 9"
		             };
		for (int i = 0; i < arithmevil.length; i++)
		{

			ArithmeticStackPlay arith = new ArithmeticStackPlay(arithmevil[i]);
			if (arith.isBalance())
			{
				System.out.println("Expression " + arithmevil[i] + " is balanced");
				arith.postfixExpression();
				System.out.println("The post fixed expression is " + arith.getPostfix());
				arith.doMath();

				System.out.println("Result is " + arith.getResult() + ". \n");
			}
			else
				System.out.println("Expression " + arithmevil[i]+" not balanced\n");
		}
	}
}