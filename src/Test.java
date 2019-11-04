import org.eclipse.jdt.internal.compiler.ASTVisitor;

public class Test {

	int sum(int x, int y){
		return x+y;
	}
	
public static void main(String[] args) {
	
	Test test=new Test();
	System.out.println(test.sum(3, 4));
	
}
}
