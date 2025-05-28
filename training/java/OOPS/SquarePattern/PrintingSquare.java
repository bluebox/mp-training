package SquarePattern;

public class PrintingSquare {

	public static void main(String[] args) {
		int side=6;
		if(side<5)
		{
			System.out.println("invalid input");
			return ;
		}
		printSquareDiagonal(side);

	}
	public static void printSquareDiagonal(int side)
	{
		char val='*';
		for(int i=0;i<side;i++)
		{
			for(int j=0;j<side;j++)
			{
				if(i== j || i== (side - 1) || j == (side - 1) || j== 0 || i==0 || (i+j)== (side-1))
				{
					System.out.print("*");
				}
				else
				{
					System.out.print(" ");
				}
			}
			System.out.println();
		}
	}

}
