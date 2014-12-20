package Controller;


import Model.ServerThread;

public class ThreadFactory{
	private static ThreadFactory tf = null;

	private ThreadFactory(){}

	public static ThreadFactory getInstance(){
		if(ThreadFactory.tf==null)
			ThreadFactory.tf = new ThreadFactory();
		return ThreadFactory.tf;
	}

	public ServerThread createServerThread(){
		return new ServerThread();
	}
}
