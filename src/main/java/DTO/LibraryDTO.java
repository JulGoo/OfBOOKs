package DTO;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class LibraryDTO {
		private String libName;
		private String address;
		private String homepage;
		private Double latitude;
		private Double longitude;
}
