package com.cgnexus.cards.service.impl;

import com.cgnexus.cards.constants.CardConstants;
import com.cgnexus.cards.dto.CardsDto;
import com.cgnexus.cards.entity.Cards;
import com.cgnexus.cards.service.CardsService;
import com.cgnexus.cards.service.exception.CardAlreadyExistsException;
import com.cgnexus.cards.service.exception.ResourceNotFoundException;
import com.cgnexus.cards.service.mapper.CardsMapper;
import com.cgnexus.cards.service.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class CardsServiceImpl implements CardsService {

    private final CardRepository cardsRepository;

    /**
     * @param mobileNumber
     */
    @Override
    public void createCard(String mobileNumber) {
        Optional<Cards> card = cardsRepository.findByMobileNumber(mobileNumber);

        if (card.isPresent()) {
            throw new CardAlreadyExistsException("Card already exists for the given mobile number" + mobileNumber);
        }

        Cards newCard = createNewCard(mobileNumber);

        cardsRepository.save(newCard);
    }

    /**
     * Creates a new credit card for the given mobile number.
     *
     * @param mobileNumber The mobile number associated with the card owner
     * @return A new {@link Cards} entity with default credit card settings
     * including randomly generated card number, default credit card type,
     * standard credit limit, and calculated available amount
     */
    private Cards createNewCard(String mobileNumber) {
        Cards newCard = new Cards();
        long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
        newCard.setCardNumber(Long.toString(randomCardNumber));
        newCard.setMobileNumber(mobileNumber);
        newCard.setCardType(CardConstants.CREDIT_CARD);
        newCard.setTotalLimit(CardConstants.NEW_CARD_LIMIT);
        newCard.setAmountUsed(0);
        newCard.setAvailableAmount(CardConstants.NEW_CARD_LIMIT);
        return newCard;
    }

    /**
     * @param mobileNumber
     * @return
     */
    @Override
    public CardsDto fetchCardDetails(String mobileNumber) {
        Cards cards = cardsRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Card", "mobileNumber", mobileNumber)
        );
        return CardsMapper.mapToCardsDto(cards, new CardsDto());


    }
}
