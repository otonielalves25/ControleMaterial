package utilidades;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Tony
 */
public class ManipulaImagem {

    /*
     * Faz redimensionamento da imagem conforme os parâmetros imgLargura e imgAltura mantendo a proporcionalidade. 
     * Caso a imagem seja menor do que os parâmetros de redimensionamento, a imagem não será redimensionada. 
     *  
     * @param caminhoImg caminho e nome da imagem a ser redimensionada. 
     * @param imgLargura nova largura da imagem após ter sido redimensionada. 
     * @param imgAltura  nova altura da imagem após ter sido redimensionada. 
     *  
     * @return Não há retorno 
     * @throws Exception Erro ao redimensionar imagem 
     ************************************************************************************************************/
    public static BufferedImage setImagemDimensao(String caminhoImg, Integer imgLargura, Integer imgAltura) {
        Double novaImgLargura;
        Double novaImgAltura = null;
        Double imgProporcao = null;
        Graphics2D g2d = null;
        BufferedImage imagem = null, novaImagem = null;

        try {
            //--- Obtém a imagem a ser redimensionada ---
            imagem = ImageIO.read(new File(caminhoImg));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(ManipulaImagem.class.getName()).log(Level.SEVERE, null, ex);
        }

        //--- Obtém a largura da imagem ---  
        novaImgLargura = (double) imagem.getWidth();

        //--- Obtám a altura da imagem ---  
        novaImgAltura = (double) imagem.getHeight();

        //--- Verifica se a altura ou largura da imagem recebida é maior do que os ---  
        //--- parâmetros de altura e largura recebidos para o redimensionamento   ---  
        if (novaImgLargura >= imgLargura) {
            imgProporcao = (novaImgAltura / novaImgLargura);//calcula a proporção  
            novaImgLargura = (double) imgLargura;

            //--- altura deve <= ao parâmetro imgAltura e proporcional a largura ---  
            novaImgAltura = (novaImgLargura * imgProporcao);

            //--- se altura for maior do que o parâmetro imgAltura, diminui-se a largura de ---  
            //--- forma que a altura seja igual ao parâmetro imgAltura e proporcional a largura ---  
            while (novaImgAltura > imgAltura) {
                novaImgLargura = (double) (--imgLargura);
                novaImgAltura = (novaImgLargura * imgProporcao);
            }
        } else if (novaImgAltura >= imgAltura) {
            imgProporcao = (novaImgLargura / novaImgAltura);//calcula a proporção  
            novaImgAltura = (double) imgAltura;

            //--- se largura for maior do que o parâmetro imgLargura, diminui-se a altura de ---  
            //--- forma que a largura seja igual ao parâmetro imglargura e proporcional a altura ---  
            while (novaImgLargura > imgLargura) {
                novaImgAltura = (double) (--imgAltura);
                novaImgLargura = (novaImgAltura * imgProporcao);
            }
        }

        novaImagem = new BufferedImage(novaImgLargura.intValue(), novaImgAltura.intValue(), BufferedImage.TYPE_INT_RGB);
        g2d = novaImagem.createGraphics();
        g2d.drawImage(imagem, 0, 0, novaImgLargura.intValue(), novaImgAltura.intValue(), null);

        return novaImagem;
    }

    //Novo método para exibir imagem na tela
    //Recebe o label que queremos exibir E a imagem como array de bytes do banco
    public static void exibirImagemNoLabel(byte[] minhaimagem, javax.swing.JLabel label) {
        //primeiro verifica se tem a imagem
        //se tem convert para inputstream que é o formato reconhecido pelo ImageIO

        if (minhaimagem != null) {
            InputStream input = new ByteArrayInputStream(minhaimagem);
            try {
                BufferedImage imagem = ImageIO.read(input);
                label.setIcon(new ImageIcon(imagem));
                label.setText("");
            } catch (IOException ex) {
                
            }
        } else {
            label.setIcon(null);
            label.setText("SEM IMAGEM");

        }

    }
    // CONVERTENDO A MIMAGEM PARA BANCO;
    // COMO CHAMA MINHA FUNÇÃO
    // modelo.setFoto(ManipulaImagem.convertePanelFotoEmObjeto(paneFoto));
    public static byte[] convertePanelFotoEmObjeto(JLabel painelFoto) {
        Image img = ((ImageIcon) painelFoto.getIcon()).getImage();
        BufferedImage bi = new BufferedImage(//é a imagem na memória e que pode ser alterada
                img.getWidth(null),
                img.getHeight(null),
                BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = bi.createGraphics();
        g2.drawImage(img, 0, 0, null);
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        try {
            ImageIO.write(bi, "jpg", buffer);
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
        return buffer.toByteArray();
    }

}
