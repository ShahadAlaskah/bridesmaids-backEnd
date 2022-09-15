package com.example.bridesmaids.service;

import com.example.bridesmaids.exception.ApiException;
import com.example.bridesmaids.model.Request;
import com.example.bridesmaids.repository.RequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RequestService {
    private final RequestRepository requestRepository;

    public List<Request> getRequests() {
        return requestRepository.findAll();
    }

    public void addRequest(Request request) {
        requestRepository.save(request);
    }

    public void updateRequest(Request request, Integer requestId) {
        Request oldRequest =requestRepository.findRequestById(requestId);

        if (oldRequest == null) {
            throw new ApiException("requestID not found");
        }
        oldRequest.setProductId(request.getProductId());
        oldRequest.setBookDate(request.getBookDate());
        oldRequest.setNote(request.getNote());
        oldRequest.setPrice(request.getPrice());
        oldRequest.setStatus(request.getStatus());
        oldRequest.setVenderId(request.getVenderId());
        oldRequest.setDateReceived(request.getDateReceived());
        oldRequest.setUserId(request.getUserId());
        oldRequest.setWayToCommunicate(request.getWayToCommunicate());

        requestRepository.save(oldRequest);


    }

    public void deleteRequest(Integer requestId) {
        Request oldRequest  = requestRepository.findRequestById(requestId);
        if (oldRequest == null) {
            throw new ApiException("requestID not found");
        }
        requestRepository.delete(oldRequest);
    }

    public List<Request> getAllByVenderId(Integer venderId) {
        List<Request> requestList  = requestRepository.findAllByVenderId(venderId);
        if (requestList == null) {
            throw new ApiException("venderId not found");
        }
        return requestList;
    }

    public List<Request> getAllByVenderIdAndAndStatus(Integer venderId, String status) {
        List<Request> requestList  = requestRepository.findAllByVenderIdAndAndStatus(status,venderId);
        if (requestList == null) {
            throw new ApiException("venderId or status not found");
        }
        return requestList;
    }

    public Request getRequestById(Integer requestId) {
        Request request  = requestRepository.findRequestById(requestId);
        if (request == null) {
            throw new ApiException("requestId or status not found");
        }
        return request;
    }

    public void changeRequestStatus(Integer requestId,String status) {
        Request request  = requestRepository.findRequestById(requestId);
        if (request == null) {
            throw new ApiException("requestId not found");
        }
        request.setStatus(status);
    }
}
