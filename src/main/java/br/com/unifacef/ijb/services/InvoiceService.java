package br.com.unifacef.ijb.services;

import br.com.unifacef.ijb.helpers.OptionalHelper;
import br.com.unifacef.ijb.mappers.InvoiceMapper;
import br.com.unifacef.ijb.models.dtos.InvoicesCreateDTO;
import br.com.unifacef.ijb.models.dtos.InvoicesDTO;
import br.com.unifacef.ijb.models.entities.Invoice;
import br.com.unifacef.ijb.repositories.ExpenseRepository;
import br.com.unifacef.ijb.repositories.InvoicesRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceService {
    @Autowired
    private InvoicesRepository invoicesRepository;
    @Autowired
    private ExpenseRepository expenseRepository;

    public Invoice save(Invoice invoice) {
        return invoicesRepository.save(invoice);
    }

    @Transactional
    public InvoicesDTO createInvoice(InvoicesCreateDTO invoiceDTO) {
        Invoice invoice = InvoiceMapper.convertInvoiceCreateDTOIntoInvoice(invoiceDTO);
        invoice = save(invoice);
        return InvoiceMapper.convertInvoiceEntityToDTO(invoice);
    }

    public List<InvoicesDTO> getAllInvoices() {
        return InvoiceMapper.convertListOfInvoiceIntoListOfInvoiceDTO(invoicesRepository.findAll());
    }

    public InvoicesDTO getInvoiceById(Integer id) {
        Invoice invoice = OptionalHelper.getOptionalEntity(invoicesRepository.findById(id));
        return InvoiceMapper.convertInvoiceEntityToDTO(invoice);
    }

    @Transactional
    public InvoicesDTO updateInvoice(Integer id, InvoicesCreateDTO invoiceDTO) {
        Invoice existingInvoice = OptionalHelper.getOptionalEntity(invoicesRepository.findById(id));

        InvoiceMapper.updateInvoice(invoiceDTO, existingInvoice);

        Invoice updatedInvoice = invoicesRepository.save(existingInvoice);

        return InvoiceMapper.convertInvoiceEntityToDTO(updatedInvoice);
    }

    @Transactional
    public void deleteInvoice(Integer id) {
        Invoice invoice = OptionalHelper.getOptionalEntity(invoicesRepository.findById(id));
        invoicesRepository.delete(invoice);
    }
}
