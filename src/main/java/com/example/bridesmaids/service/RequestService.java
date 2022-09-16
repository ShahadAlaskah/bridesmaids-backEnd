package com.example.bridesmaids.service;

import com.example.bridesmaids.exception.ApiException;
import com.example.bridesmaids.model.Request;
import com.example.bridesmaids.repository.RequestRepository;
import com.example.bridesmaids.repository.VendorRepositry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RequestService {
    private final RequestRepository requestRepository;
    private final VendorRepositry vendorRepositry;

    public List<Request> getRequests() {
        return requestRepository.findAll();
    }

    public void addRequest(Integer userId,Request request) {
        request.setUserId(userId);
        request.setStatus("new");
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
        oldRequest.setVendorId(request.getVendorId());
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

    public List<Request> getAllByVendorId(Integer userId) {
        Integer vendorId=vendorRepositry.findVendorByUserId(userId).getId();
        List<Request> requestList  = requestRepository.findAllByVendorId(vendorId);
        if (requestList == null) {
            throw new ApiException("vendorId not found");
        }
        return requestList;
    }

    public List<Request> getAllByVendorIdAndAndStatus(Integer userId, String status) {

        Integer vendorId=vendorRepositry.findVendorByUserId(userId).getId();
        List<Request> requestList  = requestRepository.findAllByVendorIdAndStatus(vendorId,status);
        if (requestList == null) {
            throw new ApiException("vendorId or status not found");
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
    //(new-underNegotiation-confirmedByVendor-confirmedByCustomer-rejected)
    public void changeRequestStatus(Integer requestId,String status) {
        if (!status.equals("underNegotiation")&&!status.equals("confirmedByVendor")&&!status.equals("confirmedByCustomer")&&!status.equals("rejected")){
            throw new ApiException("wrong status");
        }
        Request request  = requestRepository.findRequestById(requestId);
        if (request == null) {
            throw new ApiException("requestId not found");
        }
        request.setStatus(status);
    }

    public List<Request> getAllByStatus(String status) {
        return requestRepository.findAllByStatus(status);
    }

    public List<Request> getAllByUserId(Integer id) {
        return requestRepository.findAllByUserId(id);
    }

    public List<Request> getAllByUserIdAndStatus(Integer id, String status) {
        return requestRepository.findAllByUserIdAndStatus(id,status);
    }
}
