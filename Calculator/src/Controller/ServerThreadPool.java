package Controller;


import Model.ServerThread;
import java.util.ArrayList;

public class ServerThreadPool{
	private static ServerThreadPool stp;
	private ArrayList free;
	private ArrayList used;
	private final int highWaterMark = 5;
        private int extraThreads = 0;

	private ServerThreadPool(){
		this.free = new ArrayList();
		this.used = new ArrayList();
		this.fillPool();
	}

	private void fillPool(){
            ServerThread st;
		for(int i=0;i<highWaterMark;i++){
                    st = ThreadFactory.getInstance().createServerThread();
                    st.setIdThread(i);
                    System.out.println("Criada Thread " + st.getIdThread());
                    this.free.add(st);
		}
	}

	public static ServerThreadPool getInstance(){
		if(ServerThreadPool.stp==null)
			ServerThreadPool.stp = new ServerThreadPool();
		return ServerThreadPool.stp;
	}

	public ServerThread acquire() throws Exception{
            ServerThread st;
            if(!this.free.isEmpty()){
		st = (ServerThread) this.free.get(0);
                this.free.remove(st);
		this.used.add(st);
            }else{
                st = ThreadFactory.getInstance().createServerThread();
                st.setIdThread(highWaterMark+extraThreads);
                extraThreads++;
            }
            System.out.println("Id thread utilizada "+st.getIdThread());
            return st;
	}

	public void release(ServerThread serverThread) throws Exception{
            
            if(this.used.remove(serverThread)){
                ServerThread novaThread=ThreadFactory.getInstance().createServerThread();
                novaThread.setIdThread( serverThread.getIdThread());
                this.free.add( novaThread);
            }
        }
}
