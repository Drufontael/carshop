package tech.drufontael.carshop.adapter.impl;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.io.exceptions.IOException;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import tech.drufontael.carshop.adapter.PdfContractAdapter;
import tech.drufontael.carshop.model.Consignment;
import tech.drufontael.carshop.utils.Utils;

import java.io.ByteArrayOutputStream;
import java.io.StringWriter;
import java.util.Locale;

public class ItextPdfAdapter implements PdfContractAdapter {
    @Override
    public byte[] documentCreator(Consignment consignment) {
        try {
            // Configurar o Thymeleaf
            ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
            templateResolver.setPrefix("templates/"); // Pasta onde o template HTML está localizado
            templateResolver.setSuffix(".html");
            templateResolver.setTemplateMode("HTML");
            templateResolver.setCharacterEncoding("UTF-8");

            TemplateEngine templateEngine = new TemplateEngine();
            templateEngine.setTemplateResolver(templateResolver);

            // Criar o contexto do Thymeleaf com os dados
            Context context = new Context(Locale.getDefault());
            context.setVariable("consignment", consignment);

            // Processar o template HTML
            StringWriter writer = new StringWriter();
            templateEngine.process("contrato_template", context, writer);
            String htmlContent = writer.toString();

            // Converter HTML para PDF usando iText pdfHTML
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfWriter pdfWriter = new PdfWriter(baos);
            PdfDocument pdfDocument = new PdfDocument(pdfWriter);
            ConverterProperties converterProperties = new ConverterProperties();
            HtmlConverter.convertToPdf(htmlContent, pdfDocument, converterProperties);
            pdfDocument.close();

            // Salvar o PDF em um arquivo (opcional)
            // try (FileOutputStream fos = new FileOutputStream("contrato_" + consignment.getVehicle().getPlate() + ".pdf")) {
            //     baos.writeTo(fos);
            // }

            return baos.toByteArray();

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
