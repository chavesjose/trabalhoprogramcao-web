package com.chaves.webap;

import com.itextpdf.html2pdf.HtmlConverter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet("/finalizarPedido")
public class FinalizarPedidoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Gerar o PDF
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=relatorio_consumo.pdf");

        // HTML formatado conforme fornecido
        String html = """
                <html>
<head>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        h1 { color: maroon; }
        p { font-size: 14px; }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        th, td { border: 1px solid #000; padding: 8px; text-align: left; }
        th { background-color: #d3d3d3; }
        .total { font-weight: bold; background-color: #f2f2f2; }
        .center { text-align: center; font-weight: bold; }
    </style>
</head>
<body>
    <h1>Relatório de Consumo</h1>
    
    <table>
        <tr>
            <th>ORDEM</th>
            <th>DATA</th>
            <th>DPTO</th>
            <th>MATRÍCULA</th>
            <th>NR DE REQ.</th>
            <th>KILOMETRAGEM</th>
            <th>QTD</th>
            <th>CONDUTOR</th>
            <th>OBSERVAÇÃO</th>
        </tr>
        <tr><td>1</td><td>03-01-25</td><td>DES</td><td>ALW 843</td><td>800</td><td>97328</td><td><b>40</b></td><td>Amado</td><td></td></tr>
        <tr><td>2</td><td>03-01-25</td><td>Secretariado</td><td>ALX 483</td><td>798</td><td>78853</td><td><b>50.45</b></td><td>Gildo</td><td></td></tr>
        <tr><td>3</td><td>03-01-25</td><td>FA</td><td>AKZ 195</td><td>796</td><td>77287.5</td><td><b>55.94</b></td><td>Nuno Verde</td><td></td></tr>
        <tr><td>4</td><td>03-01-25</td><td>DLI</td><td>AIX 931 MP</td><td>795</td><td>86718</td><td><b>60</b></td><td>B. Domingos</td><td></td></tr>
        <tr><td>5</td><td>02-01-25</td><td>DLI</td><td>AKD 729</td><td>791</td><td>7149.6</td><td><b>61</b></td><td>Come</td><td></td></tr>
        <tr class="total">
            <td colspan="6"><b>VALOR TOTAL</b> (Diesel Consumido no mês Dezembro 2024 e Janeiro 2025 em Análise)</td>
            <td class="center">267.39</td>
            <td colspan="2">Tem um saldo por consumo na bomba</td>
        </tr>
    </table>

    <br><br>
    <table>
        <tr>
            <td style="width: 50%;"><b>Elaborado por:</b></td>
            <td><b>Verificado por:</b></td>
        </tr>
        <tr>
            <td><br><br><hr /></td>
            <td><br><br><hr /></td>
        </tr>
    </table>

</body>
</html>
                """;

        gerarPdfComHtmlConverter(html, response);
    }

    private void gerarPdfComHtmlConverter(String html, HttpServletResponse response) throws IOException {
        // Converte HTML para PDF e envia a resposta diretamente ao cliente
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            HtmlConverter.convertToPdf(new ByteArrayInputStream(html.getBytes()), baos);
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=relatorio_consumo.pdf");

            try (OutputStream os = response.getOutputStream()) {
                baos.writeTo(os);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao gerar o PDF.");
        }
    }
}
