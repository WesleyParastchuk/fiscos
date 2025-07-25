package com.example.fiscos.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.fiscos.dto.nfeApi.CompleteNFeDTO;
import com.example.fiscos.dto.openAi.ProcessedProductClassificationDTO;
import com.example.fiscos.dto.openAi.ProductClassifiedDTO;
import com.example.fiscos.model.Classification;
import com.example.fiscos.model.Invoice;
import com.example.fiscos.model.ProcessedProduct;
import com.example.fiscos.model.ProductClassification;
import com.example.fiscos.model.ProductInvoice;
import com.example.fiscos.model.QRCode;
import com.example.fiscos.model.RawProduct;
import com.example.fiscos.model.Supplier;
import com.example.fiscos.model.Tax;
import com.example.fiscos.model.User;
import com.example.fiscos.repository.ClassificationRepository;
import com.example.fiscos.repository.ProductClassificationRepository;
import com.example.fiscos.repository.UserRepository;
import com.example.fiscos.service.external.NFeApiService;
import com.example.fiscos.service.openAi.OpenAiService;

@Service
public class NFeService {

    private final UserService userService;
    private final QrCodeService qrCodeService;
    private final SupplierService supplierService;
    private final RawProductService rawProductService;
    private final NFeApiService nfeApiService;
    private final InvoiceService invoiceService;
    private final ProductInvoiceService productInvoiceService;
    private final TaxService taxService;
    private final OpenAiService openAiService;
    private final ProcessedProductService processedProductService;
    private final ClassificationRepository classificationRepository;
    private final ClassificationService classificationService;
    private final ProductClassificationRepository productClassificationRepository;

    public NFeService(SupplierService supplierService, RawProductService rawProductService,
            QrCodeService qrCodeService, UserService userService, NFeApiService nfeApiService,
            InvoiceService invoiceService, ProductInvoiceService productInvoiceService, TaxService taxService,
            OpenAiService openAiService, ProcessedProductService processedProductService,
            ClassificationRepository classificationRepository,
            ProductClassificationRepository productClassificationRepository,
            ClassificationService classificationService) {
        this.supplierService = supplierService;
        this.rawProductService = rawProductService;
        this.qrCodeService = qrCodeService;
        this.userService = userService;
        this.nfeApiService = nfeApiService;
        this.invoiceService = invoiceService;
        this.productInvoiceService = productInvoiceService;
        this.taxService = taxService;
        this.openAiService = openAiService;
        this.processedProductService = processedProductService;
        this.classificationRepository = classificationRepository;
        this.productClassificationRepository = productClassificationRepository;
        this.classificationService = classificationService;
    }

    @Transactional
    public void saveAll(Long userId, List<String> links) {
        User user = userService.checkUserExists(userId);
        links = qrCodeService.removeDuplicateLinks(links);

        List<CompleteNFeDTO> nfeList = nfeApiService.getNFeByLinks(links);

        for (int i = 0; i < links.size(); i++) {
            String link = links.get(i);
            CompleteNFeDTO nfe = nfeList.get(i);

            QRCode qrCode = qrCodeService.save(link);
            Supplier supplier = supplierService.save(nfe.getSupplier());
            Invoice invoice = invoiceService.save(nfe, user, supplier, qrCode);
            taxService.save(nfe.getTax(), invoice);
            List<RawProduct> existingRawProducts = rawProductService.getExistingRawProducts(nfe.getProducts());
            List<RawProduct> allRawProducts = new ArrayList<RawProduct>(existingRawProducts);

            List<RawProduct> newRawProducts = rawProductService.getNewRawProducts(nfe.getProducts());
            if (!newRawProducts.isEmpty()) {
                List<ProductClassifiedDTO> productsClassifieds = openAiService
                        .sendProductClassification(newRawProducts);

                List<ProcessedProduct> newProcessedProducts = processedProductService
                        .getNewProcessedProducts(productsClassifieds);
                List<ProcessedProduct> processedProducts = processedProductService.saveAll(productsClassifieds);

                

                List<RawProduct> savedRawProducts = rawProductService.saveAll(newRawProducts, processedProducts,
                        productsClassifieds);

                List<Classification> classifications = classificationRepository.findAll();
                String classificationTree = classificationService.getClassificationTreeJson();
                List<ProcessedProductClassificationDTO> classifieds = openAiService.classifyProducts(
                        processedProducts,
                        classificationTree);

                for (ProcessedProductClassificationDTO classified : classifieds) {
                    ProcessedProduct processedProduct = processedProducts.stream()
                            .filter(pp -> pp.getName().equals(classified.getProductName()))
                            .findFirst()
                            .orElseThrow(() -> new RuntimeException(
                                    "Processed product not found: " + classified.getProductName()));

                    Classification classification = classifications.stream()
                            .filter(c -> c.getId().equals(classified.getClassificationId()))
                            .findFirst()
                            .orElseThrow(() -> new RuntimeException(
                                    "Classification not found: " + classified.getClassificationId()));

                    ProductClassification productClassification = new ProductClassification();
                    productClassification.setProcessedProduct(processedProduct);
                    productClassification.setClassification(classification);
                    productClassificationRepository.save(productClassification);
                }
                
                allRawProducts.addAll(savedRawProducts);
            }

            List<ProductInvoice> productsInvoice = productInvoiceService
                    .saveAll(nfe, invoice, allRawProducts);

        }
    }

}
