package com.example.fiscos.mapper;

import org.springframework.stereotype.Component;

import com.example.fiscos.dto.nfeApi.AddressNFeDTO;
import com.example.fiscos.dto.supplier.AddressDTO;
import com.example.fiscos.model.Address;

@Component
public class AddressMapper {

    public AddressDTO toDto(Address address) {
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setId(address.getId());
        addressDTO.setStreet(address.getStreet());
        addressDTO.setNumber(address.getNumber());
        addressDTO.setComplement(address.getComplement());
        addressDTO.setDistrict(address.getDistrict());
        addressDTO.setCity(address.getCity());
        addressDTO.setUf(address.getUf());
        return addressDTO;
    }

    public Address toEntity(AddressDTO addressDTO) {
        Address address = new Address();
        address.setId(addressDTO.getId());
        address.setStreet(addressDTO.getStreet());
        address.setNumber(addressDTO.getNumber());
        address.setComplement(addressDTO.getComplement());
        address.setDistrict(addressDTO.getDistrict());
        address.setCity(addressDTO.getCity());
        address.setUf(addressDTO.getUf());
        return address;
    }

    public Address toEntity(AddressNFeDTO addressDTODto) {
        Address address = new Address();
        address.setStreet(addressDTODto.getStreet());
        address.setNumber(addressDTODto.getNumber());
        address.setComplement(addressDTODto.getComplement());
        address.setDistrict(addressDTODto.getDistrict());
        address.setCity(addressDTODto.getCity());
        address.setUf(addressDTODto.getUf());
        return address;
    }
}
