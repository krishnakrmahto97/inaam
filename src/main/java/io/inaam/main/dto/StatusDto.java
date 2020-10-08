package io.inaam.main.dto;

import io.inaam.main.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatusDto
{
    Status current;
    Status updateTo;
}
