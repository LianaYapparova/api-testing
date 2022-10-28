package pogos.petstore.pets;

import lombok.Data;

import java.util.List;

@Data

public class Pet {

    private List<String> photoUrls;
    private String name;
    private long id;
    private Category category;
    private List<TagsItem> tags;
    private String status;

    public Pet() {
    }

    public Pet(List<String> photoUrls, String name, long id, Category category, List<TagsItem> tags, String status) {
        this.photoUrls = photoUrls;
        this.name = name;
        this.id = id;
        this.category = category;
        this.tags = tags;
        this.status = status;
    }

    public  static class PetBuilder{
        private List<String> photoUrls;
        private String name;
        private long id;
        private Category category;
        private List<TagsItem> tags;
        private String status;

        public PetBuilder() {
        }

        public Pet.PetBuilder photoUrls(List<String> photoUrls) {
            this.photoUrls = photoUrls;
            return this;
        }

        public Pet.PetBuilder name(String name) {
            this.name = name;
            return this;
        }
        public Pet.PetBuilder id(long id) {
            this.id = id;
            return this;
        }

        public Pet.PetBuilder category(Category category) {
            this.category = category;
            return this;
        }
        public Pet.PetBuilder tags(List<TagsItem> tags) {
            this.tags = tags;
            return this;
        }
        public Pet.PetBuilder status(String status) {
            this.status = status;
            return this;
        }
        public Pet build() {
            return new Pet( this.photoUrls, this.name, this.id, this.category, this.tags, this.status);
        }

        @Override
        public String toString() {
            return "PetBuilder{" +
                    "photoUrls=" + photoUrls +
                    ", name='" + name + '\'' +
                    ", id=" + id +
                    ", category=" + category +
                    ", tags=" + tags +
                    ", status='" + status + '\'' +
                    '}';
        }
    }
}