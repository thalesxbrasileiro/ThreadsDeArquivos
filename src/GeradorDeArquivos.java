import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class GeradorDeArquivos {

    public static void main(String[] args) {
    	
    	// Pasta onde ficam os arquivos de origem
        File pasta = new File(System.getProperty("user.dir").concat("\\src\\PastaOrigem"));
        
        // Verifica se a pasta de origem existe. Se não existir, cria-a.
        if (!pasta.exists()) {
        	pasta.mkdir();
        	System.out.println("Pasta de origem criada!" + "\n");
        } else {
			System.out.println("Pasta de origem já existe!" + "\n");
		}
        
    	// Tamanho do arquivo (250 MB)
        int tamanhoEmBytes = (1024 * 1024) * 250;
        String pastaOrigem = System.getProperty("user.dir").concat("\\src\\PastaOrigem");
        
        // Verifica se foram passados argumentos na linha de comando
        if (args.length >= 1) {
            tamanhoEmBytes = Integer.parseInt(args[0]);
        }
        if (args.length >= 2) {
            pastaOrigem = args[1];
        }
        
        // Gera 10 arquivos
        for (int i = 1; i <= 10; i++) {
        	
            // Gera o nome do arquivo com base no timestamp atual e no índice do loop
            String nomeArquivo = "arquivo_" + System.currentTimeMillis() + "_" + i + ".txt";
            String caminhoArquivo = pastaOrigem + "/" + nomeArquivo;
            
            // Cria o arquivo e escreve nele os dados
            try (FileOutputStream outputStream = new FileOutputStream(caminhoArquivo)) {
                
            	Random random = new Random();
                byte[] dados = new byte[tamanhoEmBytes];
                
                random.nextBytes(dados);
                outputStream.write(dados);
                
                System.out.println("Arquivo " + i + " gerado com sucesso em " + caminhoArquivo);
            
            } catch (IOException e) {
                System.err.println("Erro ao gerar arquivo " + i + ": " + e.getMessage());
            }
        }
        System.out.println("\n" + "Arquivos copiados com sucesso!");
    }
}
	