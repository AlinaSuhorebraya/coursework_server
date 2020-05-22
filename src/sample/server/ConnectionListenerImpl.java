package sample.server;

import sample.dao.DAOFactory;
import sample.dao.Admin.AdminDAO;
import sample.dao.alternatives.AlternativesDAO;
import sample.SendMessage.SendMessageClass;
import sample.SendMessage.SendMessageClassImpl;

import java.io.*;
import java.net.Socket;
public class ConnectionListenerImpl implements Connection,Runnable {
    private boolean needToRun = true;
    //
    private Socket socket;

    private InputStream in;
    private OutputStream out;

    public ConnectionListenerImpl(Socket socket, ConnectionListener connectionListener) throws Exception {
        super();
        this.socket = socket;
        this.in = socket.getInputStream();
        this.out = socket.getOutputStream();

        Thread t = new Thread(this);
        t.setPriority(Thread.MIN_PRIORITY);
        t.start();
    }

    @Override //фкнция для отправки смс
    public void send(SendMessageClass sendMessageClass) {
        try {
            System.out.println("SendMessageClass sent");
            ObjectOutputStream objOut = new ObjectOutputStream(out);
            objOut.writeObject(sendMessageClass);
            objOut.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    //закрыть соединеие
    @Override
    public void close() {
        try {
            this.needToRun = false;
            this.socket.close();
            this.in.close();
            this.out.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            System.out.println("Соединение закрыто!");
        }
    }
    //основная функция работы сервера

    @Override
    public void run() {

        while (needToRun) {
            try {

                int amount = in.available();//поток

                if (amount != 0) {//проверяет есть ли что-то в потоке

                    ObjectInputStream objIn = new ObjectInputStream(in);//набор байтов инфы которую передали

                    SendMessageClass msq = (SendMessageClassImpl) objIn.readObject();

                    System.out.println("We takes SendMessage with type: " + msq.getType());//какого рода сообщ

                    readMessage(msq);

                } else {
                    Thread.sleep(300);//если поток пустой то ждем
                }
            } catch (IOException ex  )

            {
                ex.printStackTrace();
            }
            catch (InterruptedException ex  )
                    {
                        ex.printStackTrace();
                    }
           catch( ClassNotFoundException ex )
                    {
                       ex.getException();
                    }



        }
    }


    public synchronized void readMessage(SendMessageClass msq) {

        AlternativesDAO alternativeDAO = DAOFactory.getInstance().getAlternativeDAO();
        AdminDAO adminDAO = DAOFactory.getInstance().getAdminDAO();

        System.out.println("Получили тип:" + msq.getType());
        switch (msq.getType()) {
            case SendMessageClass.ADD_NEW_DETAIL: {
                alternativeDAO.insertAlternative(msq.getAlternative());

            }
            break;
            case SendMessageClass.SHOW_ALL_DETAIL: {

                SendMessageClass mes = new SendMessageClassImpl(SendMessageClass.SHOW_ALL_DETAIL, alternativeDAO.getAlternativeList());
                alternativeDAO.getAlternativeList().forEach(System.out::println);
                send(mes);

            }
            break;
            case SendMessageClass.DELETE_DETAIL: {
                alternativeDAO.deleteAlternative(msq.getId());
            }
            break;
            case SendMessageClass.UDDATE_DETAIL:{
                alternativeDAO.update(msq.getAlternative());
            }
            break;
            case SendMessageClass.ADMIN_ADD_NEW:{
                adminDAO.insertAdmin(msq.getAdmin());

            }
            break;
            case  SendMessageClass.ADMIN_SHOW_ALL:{


                SendMessageClass mes = new SendMessageClassImpl(adminDAO.getAdminList(), SendMessageClass.ADMIN_SHOW_ALL);
                adminDAO.getAdminList().forEach(System.out::println);
                send(mes);
            }
            break;
            case SendMessageClass.ADMIN_DELETE :{
                adminDAO.deleteAdmin(msq.getAdmin());
            }
            break;
        }
    }
}
