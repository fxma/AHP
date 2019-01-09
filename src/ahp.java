import Jama.EigenvalueDecomposition;
import Jama.Matrix;


public class ahp {
	
	
	public void  panduanjuzhen(){}
//	该函数用于构造判断矩阵；
	public void tezheng(){}
//	用于计算特征值
	public void tezhengxiangliang(){}
//	用于计算特征向量
	public void yizhixingjiancha(){}
//	用于进行一致性检查C1和CR
	public void pingguzhibiaoquanzhong(){}
//	输出评估指标权重
//	主函数用于测试
	public void ahpy(){
//		
		double panduanjuzhen[ ][ ]={{1,2,2,2,3},{0.5,1,1,1,2},{0.5,1,1,1,2},{0.5,1,1,1,2},{0.333333,0.2,0.2,0.2,1}};

//		对5个元素对准则C的判断矩阵A，求这5个元素相对于C的相对权重w1,w2,w3,w4,w5;
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

//		到此步，得到了w的权重矩阵W=(w1,w2,w3,w4,w5)T;
//		判断矩阵的一致性检验
//		double [][] a=new double[5][5];
//		a=panduanjuzhen;
//		
//		double [][] J=new double[5][5];
//		double eps=0.00000001;
//		jaccobi.myjaccobimat(a,J,5,eps);
//		System.out.println("dfa");
//		System.out.println(" 特征值"+a[0][0]+"和"+a[1][1]+"和"+a[2][2]);
//		g.drawString(" 特征值"+a[0][0]+"和"+a[1][1]+"和"+a[2][2], 10,20);	
		Matrix a=new Matrix(panduanjuzhen);
		EigenvalueDecomposition b=a.eig();
		Matrix landa=b.getV();
		double real[]=b.getRealEigenvalues();
//		保存特征值的实部
		double image[]=b.getImagEigenvalues();
//		int length,m;
//		for(m=0;m<real.length;m++){
//			System.out.println(m+":"+real[m]);
//			System.out.println(m+":"+image[m]);
//		}
		int n;double max=0;
//		max 保存最大特征值
		max=real[0];
		for(n=1;n<real.length;n++){
			if(real[n]>=max){
				max=real[n];
			}
		}
//		System.out.println("dasf"+max);

//	到该部，球出了最大特征值
//		计算一致性指标
		double ci=(max-5)/4;
//		即ci=(max-n)/n-1;
		double r5=1.12;
//		r5是有T.L.Saaty给出的平均随即一致性指数
		double cr=ci/r5;
	/*	if(cr<0.1){
			System.out.println("ok");
		}
		测试部分，测试结果输出为ok，则说明判断矩阵合理，则继续下步
		否则，重新对判断矩阵进行构建
		
		*/
		
//		测试结果为ok，则w数组中保存的即为权重；
		String name[]={"时间","提示次数","正确率1","正确率2","预备知识掌握程度"};
		System.out.println("这五个因素对应的权重分别是：");
		int h;
		double sum=0;
		for(h=0;h<w.length;h++){
			System.out.print(name[h]+"——权重——：");
			System.out.print(w[h]+"\n");
			sum+=w[h];
		}
		System.out.println("权重和为："+sum);
	}
	public static void main(String[] args){
		
//		System.out.println("hello");
		 ahp a = new ahp();
		 a.ahpy();
		
	}
}
