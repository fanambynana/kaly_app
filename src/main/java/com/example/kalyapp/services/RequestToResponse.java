package com.example.kalyapp.services;

public interface RequestToResponse<Req, Res> {
    Res requestToResponse(Req req);
}
