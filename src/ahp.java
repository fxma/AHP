import Jama.EigenvalueDecomposition;
import Jama.Matrix;


public class ahp {
	
	
	public void  panduanjuzhen(){}
//	�ú������ڹ����жϾ���
	public void tezheng(){}
//	���ڼ�������ֵ
	public void tezhengxiangliang(){}
//	���ڼ�����������
	public void yizhixingjiancha(){}
//	���ڽ���һ���Լ��C1��CR
	public void pingguzhibiaoquanzhong(){}
//	�������ָ��Ȩ��
//	���������ڲ���
	public void ahpy(){
//		
		double panduanjuzhen[ ][ ]={{1,2,2,2,3},{0.5,1,1,1,2},{0.5,1,1,1,2},{0.5,1,1,1,2},{0.333333,0.2,0.2,0.2,1}};

//		��5��Ԫ�ض�׼��C���жϾ���A������5��Ԫ�������C�����Ȩ��w1,w2,w3,w4,w5;
		int i,j,k;
		double sum1=0,sum2=0;
		double shang=0;
		double wi=0;
		double w[]={0,0,0,0,0};
		for(i=0;i<5;i++){
			for(j=0;j<5;j++){
				for(k=0;k<5;k++){
					sum1=sum1+panduanjuzhen[k][j];
				}
				shang=panduanjuzhen[i][j]/sum1;
				sum2=sum2+shang;
			}	
			wi=sum2/5;
			w[i]=wi;

		}			

//		���˲����õ���w��Ȩ�ؾ���W=(w1,w2,w3,w4,w5)T;
//		�жϾ����һ���Լ���
//		double [][] a=new double[5][5];
//		a=panduanjuzhen;
//		
//		double [][] J=new double[5][5];
//		double eps=0.00000001;
//		jaccobi.myjaccobimat(a,J,5,eps);
//		System.out.println("dfa");
//		System.out.println(" ����ֵ"+a[0][0]+"��"+a[1][1]+"��"+a[2][2]);
//		g.drawString(" ����ֵ"+a[0][0]+"��"+a[1][1]+"��"+a[2][2], 10,20);	
		Matrix a=new Matrix(panduanjuzhen);
		EigenvalueDecomposition b=a.eig();
		Matrix landa=b.getV();
		double real[]=b.getRealEigenvalues();
//		��������ֵ��ʵ��
		double image[]=b.getImagEigenvalues();
//		int length,m;
//		for(m=0;m<real.length;m++){
//			System.out.println(m+":"+real[m]);
//			System.out.println(m+":"+image[m]);
//		}
		int n;double max=0;
//		max �����������ֵ
		max=real[0];
		for(n=1;n<real.length;n++){
			if(real[n]>=max){
				max=real[n];
			}
		}
//		System.out.println("dasf"+max);

//	���ò���������������ֵ
//		����һ����ָ��
		double ci=(max-5)/4;
//		��ci=(max-n)/n-1;
		double r5=1.12;
//		r5����T.L.Saaty������ƽ���漴һ����ָ��
		double cr=ci/r5;
	/*	if(cr<0.1){
			System.out.println("ok");
		}
		���Բ��֣����Խ�����Ϊok����˵���жϾ������������²�
		�������¶��жϾ�����й���
		
		*/
		
//		���Խ��Ϊok����w�����б���ļ�ΪȨ�أ�
		String name[]={"ʱ��","��ʾ����","��ȷ��1","��ȷ��2","Ԥ��֪ʶ���ճ̶�"};
		System.out.println("��������ض�Ӧ��Ȩ�طֱ��ǣ�");
		int h;
		double sum=0;
		for(h=0;h<w.length;h++){
			System.out.print(name[h]+"����Ȩ�ء�����");
			System.out.print(w[h]+"\n");
			sum+=w[h];
		}
		System.out.println("Ȩ�غ�Ϊ��"+sum);
	}
	public static void main(String[] args){
		
//		System.out.println("hello");
		 ahp a = new ahp();
		 a.ahpy();
		
	}
}
