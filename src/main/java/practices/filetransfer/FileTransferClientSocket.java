package practices.filetransfer;

import practices.files.DataPathGetter;
import practices.sockets.BidirectionalClientSocket;

import java.io.*;
import java.net.Socket;
import java.nio.file.Path;

public class FileTransferClientSocket extends BidirectionalClientSocket {
    public FileTransferClientSocket(boolean printSocketMessages) {
        super(printSocketMessages);
    }

    public void send(String rootPath, String filename, FileTransferClientSocketBuffers bufferSize) {
        try{
        File file = new File(Path.of(rootPath, filename).toString());

        int count;
        byte[] buffer = new byte[bufferSize.getSize()];

        FileInputStream fileInputStream= new FileInputStream(file);
        BufferedInputStream fileBufferedInputStream =new BufferedInputStream(fileInputStream);

        printWriter.println(FileTransferClientSocketMessages.SEND);
        printWriter.println(filename);

        while ((count = fileBufferedInputStream.read(buffer)) > 0) {
            outputStream.write(buffer, 0, count);
            outputStream.flush();
        }

        printWriter.println(FileTransferClientSocketMessages.END);

        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }}