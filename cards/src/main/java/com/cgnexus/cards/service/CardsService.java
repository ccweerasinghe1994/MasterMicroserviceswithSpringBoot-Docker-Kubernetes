package com.cgnexus.cards.service;

import com.cgnexus.cards.dto.CardsDto;

public interface CardsService {

    void createCard(String mobileNumber);

    CardsDto fetchCardDetails(String mobileNumber);
}
