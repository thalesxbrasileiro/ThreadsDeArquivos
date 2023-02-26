import java.io.*;

public class CopiarArquivosComUmThread {

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

        // Copia cada arquivo da pasta de origem para a pasta de destino
        for (File arquivo : listaDeArquivos) {
            
        	File arquivoDestino = new File(pastaDestino.getAbsolutePath() + "/" + arquivo.getName());
            
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
        }

        long tempoFinal = System.currentTimeMillis();
        long tempoTotal = tempoFinal - tempoInicio;
        
        System.out.println("\n" + "Tempo utilizado com 1 Thread: " + tempoTotal + " milissegundos!");
    }
}
