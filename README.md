## Vectorizer

[Medium Article](https://medium.com/@aleksgladun4/implementing-vector-similarity-search-engine-ee626b84bc5)

### Intro
Vectorizer is a simple service that creates embeddings from your text data, stores vectors in memory along with their text associations and performs similarity search. 

### Example
Text data `sentences.txt`:
```
The cat sat on the mat.
A feline is resting on the rug.
Kittens love to play with yarn.
Dogs often chase their tails.
I enjoy reading books by the beach.
Reading near the ocean is my favorite activity.
She sold seashells by the seashore.
He bought some shells from the beach vendor.
Science is the poetry of reality.
Physics explains the fundamental laws of the universe.
She baked a chocolate cake for her birthday.
I prefer chocolate cookies over vanilla.
The football game ended with a surprising twist.
Soccer matches can be very unpredictable.
The mountain peak was covered with snow.
Hikers often face challenges in snowy terrains.
He loves listening to classical music.
Jazz and blues are her favorite genres.
Learning languages opens up new worlds.
She speaks Spanish, French, and Italian fluently.
```
Code example:
```java
var textParser = new FileTextParser("sentences.txt");
var text = textParser.parse();

var embeddingsProvider = new OpenAIEmbeddingsProvider(System.getenv("OPEN_API_KEY"), "text-embedding-ada-002");

var vectorizer = Vectorizer.from(text, embeddingsProvider);

var input = "I love playing guitar";

var similarText = vectorizer.similaritySearch(input);

similarText.forEach(System.out::println);
```
Program output:
```
He loves listening to classical music.
Jazz and blues are her favorite genres.
I enjoy reading books by the beach.
```
### Extension points
`TextParser` for parsing text from various sources

`EmbeddingsProvider`  to introduce a new model that generates embeddings from textual content

`SimilarityCalculator` for implement a new similarity calculation algorithm

`VectorStorage` to provide your own storage mechanism for vectors

