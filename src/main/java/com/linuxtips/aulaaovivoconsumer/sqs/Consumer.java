package com.linuxtips.aulaaovivoconsumer.sqs;


import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.QueueDoesNotExistException;
import com.amazonaws.services.sqs.model.ReceiveMessageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service

public class Consumer {
    @Autowired
    private AmazonSQS amazonSQSClient;

    private final String finalizarCompra = "https://sqs.sa-east-1.amazonaws.com/771177158205/finaliza-compra-linux-tips-aula-ao-vivo";

    public Consumer() {
    }


    @Scheduled(fixedDelay = 2000)
    public void receberCompraFinalizada() {
        try {
            ReceiveMessageResult receiveMessageResult = amazonSQSClient.receiveMessage(finalizarCompra);
            if (!receiveMessageResult.getMessages().isEmpty()) {
                Message message = receiveMessageResult.getMessages().get(0);
                pedidoFinalizado(message.getBody());
                amazonSQSClient.deleteMessage(finalizarCompra, message.getReceiptHandle());
            }
        } catch (QueueDoesNotExistException e) {
            System.out.println("Fila não existe " + e.getMessage());
        }
    }

    private void pedidoFinalizado(String body) {
        System.out.println("Pedido finalizado, dados do pedido" + body);
    }
}
