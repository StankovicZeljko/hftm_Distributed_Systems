package ch.hftm.dto.validation;

import lombok.Builder;

import javax.validation.groups.Default;

public interface ValidationEntryGroups {

    interface Post extends Default{};

    interface Put extends Default {};

}
