import java.applet.Applet;
import java.awt.Graphics;

public class  jaccobi extends Applet
{
public static  void myjaccobimat(double [][] a,double [][] J,int n,double eps)
{
	double t,s,c,max,temp;
	int i,j,p,q;
	double [] b1= new double[n];
	double [] b2= new double[n];
	for(i=0;i<n;i++)
		for(j=0;j<n;j++)
		{
			if(i==j)
				J[i][j]=1;
			else J[i][j]=0;
			
		}
	while(true)
	{
		max=0;p=0;q=0;
		for(i=0;i<n-1;i++)
			for(j=i+1;j<n;j++)
			{
				temp=Math.abs(a[i][j]);
				if(temp>max)
				{
					max=temp;
					p=i;
					q=j;
					
				}
			}
		if(max<eps) break;
		t=(a[p][p]-a[q][q])/a[p][q];
		if(t==0)
			c=s=Math.sqrt(2)/2;
		else
		{
			if(t<0)
				t=-1.0/(-t+Math.sqrt(1+t*t));
			else
				t=1.0/(t+Math.sqrt(1+t*t));
			c=1.0/Math.sqrt(1+t*t);
			s=t*c;
		}
		for(i=0;i<n;i++)
		{
			if(i==p)
				b1[i]=a[p][p]*c*c+a[q][q]*s*s+a[p][q]*2*s*c;
			else if(i==q)
				b1[i]=(a[q][q]-a[p][p])*s*c+a[p][q]*(2*c*c-1);
			else
				b1[i]=a[i][p]*c+a[i][q]*s;
		}
		for(i=0;i<n;i++)
		{
			if(i==p)
				b2[i]=b1[q];
			else if(i==q)
				b2[i]=a[p][p]*s*s+a[q][q]*c*c-a[p][q]*2*s*c;
			else 
				b2[i]=-a[i][p]*c+a[i][q]*c;
		}
		for(i=0;i<n;i++)
		{
			a[i][p]=a[p][i]=b1[i];
			a[i][q]=a[q][i]=b2[i];
		}
		for(i=0;i<n;i++)
		{
			b1[i]=J[i][p]*c+J[i][q]*s;
			b2[i]=-J[i][p]*s+J[i][q]*c;
		}
		for(i=0;i<n;i++)
		{
			J[i][p]=b1[i];
			J[i][q]=b2[i];
			
		}
	}
	System.out.println(max);
}	

	

public void paint (Graphics g)
{
	double a[ ][ ]={{1,2,2,2,3},{0.5,1,1,1,2},{0.5,1,1,1,2},{0.5,1,1,1,2},{0.333333,0.2,0.2,0.2,1}};
	double [][] J=new double[5][5];
	double eps=0.00000001;
	myjaccobimat(a,J,5,eps);
	g.drawString(" 特征值"+a[0][0]+"和"+a[1][1]+"和"+a[2][2], 10,20);	
}

}
