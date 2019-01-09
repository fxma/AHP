import java.util.Scanner;

import Jama.EigenvalueDecomposition;
import Jama.Matrix;


public class aph_new {
public  static int n=6;
public final static double []RI={0,0,0.58,0.90,1.12,1.24,1.32,1.41,1.45,1.49,1.51};
//RI��ֻ��n�йصģ���һ�����ݱ���Բ鵽RI�Ƕ���,����n�Ĳ�ͬRI��ͬ
public static double [][]B=new double[n][n];
//B[][] �����жϾ���
public static double lambda=0;
//Lambda �� �ˣ� lambda�����֤�������ֵ
public static double CI=0;
//CI:����һ����ָ��
public static double CR=0;
/*CR:���һ���Ա��ʣ�
 * CR<0.1 �жϾ�����п��Խ��ܵ�һ����
 * CR>0.1 ��Ҫ�����޸��жϾ���һ���Բ�����
 * */
public static double []weight=new double[n];
/**
 * �����Ǻ������岿��
 */
public static void getN(){
	System.out.println("������������");
	Scanner scanner=new Scanner(System.in);
	String nString=scanner.nextLine();
	String nlineReader=scanner.nextLine();
	n=Integer.valueOf(nString);
	B=new double[n][n];
}
public static void getB(){
	System.out.println("�������жϾ���,�������룬Ԫ�ؼ��Կո�����ÿ���Իس���Ϊ�������������");
	Scanner scanner=new Scanner(System.in);
	for(int i=0;i<n;i++){
		System.out.println("����� "+i+" ��");
//		System.out.println("");
		String lineString=scanner.nextLine();
		System.out.println("��������ǣ�"+lineString);
		int start_index=0;
		for(int j=0;j<n;j++) {
			if(start_index!=0){
			start_index+=1;
			}
			System.out.println("start_index:"+start_index);
			int end_index=0;
			if(j<n-1){
				end_index=lineString.indexOf(" ");	
			}else{
				end_index=lineString.length();
			}               
			System.out.println("end_index:"+end_index);
			String  tmp=lineString.substring(start_index, end_index);
			lineString=lineString.replaceFirst(" ", "#");
			System.out.println("lineString:"+lineString);
			start_index=end_index;
			System.out.println("tmp:"+tmp);
			tmp=tmp.replaceAll(" ", "");
			double t=Double.valueOf(tmp);
			System.out.println(t);
			B[i][j]=t;
			System.out.println("--------------------"+B[i][j]);
		}
	}
	display(B);
}
public static void display(double [][]a){
	for(int i=0;i<a.length;i++){
		for(int j=0;j<a[i].length;j++){
			System.out.print(a[i][j]+" ");
		}
		System.out.println("\n");
	}
}
public static void display_one(double []a){
	for(int i=0;i<a.length;i++){
		
			System.out.print(a[i]+" ");
		}
		System.out.println("\n");
	
}
public static void getMaxLambda(){
//	��ȡ�жϾ���B���������ֵ;
	Matrix a=new Matrix(B);
	EigenvalueDecomposition b=a.eig();
	double real[]=b.getRealEigenvalues();
//	��������ֵ��ʵ��
	int n;double max=0;
//	max �����������ֵ
	max=real[0];
	for(n=1;n<real.length;n++){
		if(real[n]>=max){
			max=real[n];
		}
	}
	lambda=max;
}
public static void getCI(){
//	��������һ����ָ��CI
	CI=(lambda-n)/(n-1);
}
public static void getCR(){
	if(n<3){
		CR=0;
	}else{
		CR=CI/RI[n-1];
	}
	
}
public static boolean whetherSatisfied(){
	return CR>0.1?false:true;
}
public static void getWeight(){
	double sum1=0,sum2=0;
	double []wi=new double [n];
	double [][]wij=new double [n][n];
	double sum3=0;
	for(int i=0;i<n;i++){
		int j;
		for(j=0;j<n;j++){
			sum1=sum1+B[j][i];
	//		wij[i][j]=B[i][j]/sum1;
		}
		for(j=0; j < n; ++ j)
		{
			wij[j][i]=B[j][i]/sum1;
		}
		sum1 = 0;
	}
	for(int i=0;i<n;i++){
		for(int j=0;j<n;j++){
			sum2=sum2+wij[i][j];
		}
		wi[i]=sum2;
		sum2 = 0;
	}
	for(int i=0;i<n;i++){
		sum3=sum3+wi[i];
	}
	for(int i=0;i<n;i++){
		System.out.println("wi:"+n);
		System.out.println("getWeight:i="+i);
		weight[i]=wi[i]/sum3;
	}
	display_one(weight);
}

public static void main(String args[]){
//	getN();
	String lineString="";
	do {
		System.out.println("**********�����quit��������ַ���ʼ**********");
		Scanner scanner=new Scanner(System.in);
		lineString=scanner.nextLine();
		if(lineString.equals("quit")){
			 break;
		}
		getN();
		getB();
		getMaxLambda();
		getCI();
		getCR();
		System.out.println("CR��ֵ�ǣ�"+CR);                          
		if(whetherSatisfied()){
			System.out.println("yes-------the end---------");
 		}else{
			System.out.println("no-------- again --------");
		}
	} while (!whetherSatisfied());
	
	if(whetherSatisfied()){
		System.out.println("**********���ս��ȨֵΪ**********");
		getWeight();
	}
	
}
}                 
/*
1 5 0.25 6 6 7
0.2 1 0.143 0.333 2 3
4 7 1 5 7 8
0.167 3 0.2 1 3 4
0.167 0.5 0.143 0.333 1 3
0.143 0.333 0.125 0.25 0.333 1
*/