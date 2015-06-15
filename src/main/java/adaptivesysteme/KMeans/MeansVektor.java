package adaptivesysteme.KMeans;


public class MeansVektor {
	
	private double[] vector;
	
	private int dimension;
	
	private double typ;
	
	private CodeBook CodeBook;
	
	public CodeBook getCodeBook() {
		return CodeBook;
	}

	public void setCodeBook(CodeBook codeook) {
		CodeBook = codeook;
	}

	public MeansVektor(int typ, int dim, double[] vec){
		this.dimension = dim;
		this.vector = vec;
		this.typ = typ;
	}


	public double getValue(int index){
		if(index < dimension)
			return vector[index];
		else
			return 0;
	}
	public double getTyp() {
		return typ;
	}

	public void setTyp(double typ) {
		this.typ = typ;
	}
	
	public double[] getVector(){
		return vector;
	}
	
	public String toString(){
		StringBuffer b = new StringBuffer();
		for(double d: vector){
			b.append(d + "; ");
		}
		return b.toString();
	}
	
}
