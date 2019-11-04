/**
 * @author Sarah Kollins, Laurel Sexton
 *
 */
/*import org.eclipse.core.commands.AbstractHandler; 
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;*/
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;


public class ASTHelper {
	 private MethodVisitor visitor = new MethodVisitor();
    private static final String JDT_NATURE = "org.eclipse.jdt.core.javanature";

   /* @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        IWorkspace workspace = ResourcesPlugin.getWorkspace();
        IWorkspaceRoot root = workspace.getRoot();
        // Get all projects in the workspace
        IProject[] projects = root.getProjects();
        // Loop over all projects
        for (IProject project : projects) {
            try {
                if (project.isNatureEnabled(JDT_NATURE)) {
                    analyseMethods(project);
                }
            } catch (CoreException e) {
                e.printStackTrace();
            }
        }
        return null;
    }*/
/*public ASTHelper(IProject project)throws JavaModelException{
	analyseMethods(project);
}*/
   /* private void analyseMethods(IProject project) throws JavaModelException {
        IPackageFragment[] packages = JavaCore.create(project).getPackageFragments();
        // parse(JavaCore.create(project));
        for (IPackageFragment mypackage : packages) {
            if (mypackage.getKind() == IPackageFragmentRoot.K_SOURCE) {
                createAST(mypackage);
            }

        }
    }*/

    public void createAST(File file) throws JavaModelException, IOException {
    	String fileString = fileToString(file);
        //for (ICompilationUnit unit : mypackage.getCompilationUnits()) {
            // now create the AST for the ICompilationUnits
            CompilationUnit parse = parse(fileString);
            parse.accept(visitor);
            
            /*for (MethodDeclaration method : visitor.getMethods()) {
                System.out.print("Method name: " + method.getName()
                        + " Return type: " + method.getReturnType2());
            }*/

        //}
    }
    
    public List<MethodDeclaration> getMethod() {
    	return visitor.getMethods();
    }

    /**
     * Reads a String and creates the AST DOM for manipulating the
     * Java source file
     * 
     * REALLY THE ONE CREATING THE AST
     * 
     * @param fileString
     * @return
     */
    public static CompilationUnit parse(String fileString) {
        ASTParser parser = ASTParser.newParser(AST.JLS3);
        parser.setKind(ASTParser.K_COMPILATION_UNIT);
        parser.setSource(fileString.toCharArray());
        parser.setResolveBindings(true);
        return (CompilationUnit) parser.createAST(null); // parse
    }
    
    public String fileToString(File file) throws IOException {
    	BufferedReader br = new BufferedReader(new FileReader(file));
    	String appendedString = "";
    	while(br.readLine() != null) {
    		appendedString += br.readLine();
    	}
    	return appendedString;
    }
}