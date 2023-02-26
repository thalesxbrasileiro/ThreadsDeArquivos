import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CopiarArquivosComDezThreads {

    public static void main(String[] args) {
    	
        long tempoInicio = System.currentTimeMillis();

        File pastaOrigem = new File(System.getProperty("user.dir").concat("\\src\\PastaOrigem"));
        File pastaDestino = new File(System.getProperty("user.dir").concat("\\src\\PastaDestino"));

        // Verifica se a pasta de destino existe. Se não existir, cria-a.
        if (!pastaDestino.exists()) {
            pastaDestino.mkdir();
        }

        // Obtém a lista de arquivos da pasta de origem
        File[] listaDeArquivos = pastaOrigem.listFiles();

        // Cria um pool de threads com 10 threads
        ExecutorService threads = Executors.newFixedThreadPool(10);

        // Para cada arquivo da pasta de origem, cria uma tarefa para copiá-lo utilizando uma das threads do pool
        for (File arquivo : listaDeArquivos) {
        	
            File arquivoDestino = new File(pastaDestino.getAbsolutePath() + "/" + arquivo.getName());
            
            threads.execute(() -> {
                try {
                    FileInputStream inputStream = new FileInputStream(arquivo);
                    FileOutputStream outputStream = new FileOutputStream(arquivoDestino);
                    
                    System.out.println(arquivoDestino);
                    
                    byte[] buffer = new byte[1024];
                    int tamanho;
                    
                    while ((tamanho = inputStream.read(buffer)) > 0) {
                        outputStream.write(buffer, 0, tamanho);
                    }
                    
                    inputStream.close();
                    outputStream.close();
                    
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }

        // Encerra o pool de threads após todas as tarefas serem concluídas
        threads.shutdown();

        // Aguarda todas as tarefas serem concluídas
        while (!threads.isTerminated()) {}

        long tempoFinal = System.currentTimeMillis();
        long tempoTotal = tempoFinal - tempoInicio;
        
        System.out.println("\n" + "Tempo utilizado com 10 Threads: " + tempoTotal + " milissegundos!");
    }
}