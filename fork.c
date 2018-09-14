#include<stdio.h>
#include<stdlib.h>
void main()
{
  int p1,p2,p3,p4,p5;
  p1=fork();
  if(p1==0)
    {//c1
	//printf("child c1 starts\n");	
	p2=fork();
	if(p2==0)
	  {//c3
		printf("child c3 starts\n");
		c3();
		
	}
	else{//c1
		printf("parent c1 starts\n");	
		c1();
	 }
   }  
   else
    {//p
	printf("parent p1 starts\n");	
    p();
        p3=fork();
		if(p3==0)//c2
		  {
                	printf("child c2 starts\n");
			p4=fork();
		      if(p4==0)//c2
		       {
                	  printf("child c2 starts\n");
			   c2();
		        }
		     else{//c4
			 printf("parent c4 starts\n");
			 c4();
			}
		}
  
    }
}
void p()
{
  for(int i=100;i<300;i++)
	printf("pc1--%d pid %d\n",i,getpid());
}
void c1()
{
  for(int i=20;i<40;i++)
	printf("pc1--%d pid %d\n",i,getpid());
}
void c2()
{
  for(int i=40;i<60;i++)
	printf("pc1--%d pid %d\n",i,getpid());
}
void c3()
{
  for(int i=60;i<80;i++)
	printf("pc1--%d pid %d\n",i,getpid());
}
void c4()
{
  for(int i=80;i<100;i++)
	printf("pc1--%d pid %d\n",i,getpid());
}

