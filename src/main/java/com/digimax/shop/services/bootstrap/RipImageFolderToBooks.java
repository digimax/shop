package com.digimax.shop.services.bootstrap;

import com.digimax.shop.entities.domain.item.AbstractItem;
import com.digimax.shop.entities.domain.item.Book;
import com.digimax.shop.entities.user.Author;
import com.digimax.shop.services.domain.BookService;
import com.digimax.shop.structural.RecursiveFileListIterator;
import org.apache.commons.lang.WordUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by jon on 2014-03-21.
 */
public class RipImageFolderToBooks {

    private static final Logger LOGGER = LoggerFactory.getLogger(RipImageFolderToBooks.class);
    
    //System property com.digimax.shop.rip.root
    private static String BOOK_RIP_ROOT_FOLDER = System.getProperty("com.digimax.shop.rip.root")!=null?
            System.getProperty("com.digimax.shop.rip.root"): "/dig/wrk/maw_raw/pro/";
    //System property com.digimax.shop.image.folder
    public static String APP_IMAGE_FOLDER = System.getProperty("com.digimax.shop.image.folder")!=null?
            System.getProperty("com.digimax.shop.image.folder"): "/dig/wrk/maw_raw/app_images_folder/";


    public static Map<AbstractItem, String> rip(BookService bookService) {
        long startTime = System.currentTimeMillis();
        Map<AbstractItem, String> itemLocationMap = new HashMap<>();
        File rootFolder = new File(BOOK_RIP_ROOT_FOLDER);
        Iterator<File> fileIterator = new RecursiveFileListIterator(rootFolder);
        while (fileIterator.hasNext()) {
            File file = fileIterator.next();
            String fileAbsolutePath = file.getAbsolutePath();
            LOGGER.debug("examining File :: {}", fileAbsolutePath);
            if (fileAbsolutePath.toLowerCase().contains(".png") && !fileAbsolutePath.toUpperCase().contains("SPINE")) {
                String fileNameWithLocation =
                        fileAbsolutePath.substring(BOOK_RIP_ROOT_FOLDER.length(), fileAbsolutePath.length());
                fileNameWithLocation = fileNameWithLocation.replace('^', '~');
                LOGGER.debug("  processing File :: {}", fileNameWithLocation);
                String locationName = fileNameWithLocation.substring(0, fileNameWithLocation.lastIndexOf(File.separator));
                locationName = locationName.replaceAll(File.separator, ".");
                LOGGER.debug("      locationName :: {}", locationName);
                String fileName = fileNameWithLocation.substring(locationName.length()+1, fileNameWithLocation.length());
                LOGGER.debug("      fileName :: {}", fileName);
                String authorsName = (fileName.indexOf('~')>=0 && fileName.lastIndexOf('.')>=0)?
                        fileName.substring(fileName.indexOf('~')+1, fileName.lastIndexOf('.')):
                        null;
                LOGGER.debug("      authorsName :: {}", authorsName);

                String bookTitle = (fileName.indexOf('~')>0)?
                        fileName.substring(0, fileName.indexOf('~')).replace('_', ' '):
                        fileName.substring(0,fileName.lastIndexOf('.')).replace('_', ' ');
                String bookSubTitle = null;
                if (bookTitle.contains("[")&&bookTitle.contains("]")) {
                    bookSubTitle = bookTitle.substring(bookTitle.indexOf('[')+1, bookTitle.indexOf(']'));
                    bookTitle = bookTitle.substring(0, bookTitle.indexOf('['));
                }

                LOGGER.debug("      title :: {}", bookTitle);
                String firstAuthorLastName = null;
                String firstAuthorFirstName = null;
                String secondAuthorLastName = null;
                String secondAuthorFirstName = null;

                if (authorsName!=null) {
                    boolean isThereASecondAuthor = authorsName.contains("&");
                    if (isThereASecondAuthor) {
                        String firstAuthorFullName = authorsName.substring(0, authorsName.indexOf("&"));
                        String secondAuthorFullName = authorsName.substring(authorsName.indexOf("&")+1, authorsName.length());
                        firstAuthorFirstName = (firstAuthorFullName.indexOf("_")>0)?firstAuthorFullName.substring(0, firstAuthorFullName.indexOf("_")):null;
                        firstAuthorLastName = (firstAuthorFullName.indexOf("_")>0)?firstAuthorFullName.substring(firstAuthorFullName.indexOf("_")+1, firstAuthorFullName.length()):firstAuthorFullName;
                        secondAuthorFirstName = (secondAuthorFullName.indexOf("_")>0)?secondAuthorFullName.substring(0, secondAuthorFullName.indexOf("_")):null;
                        secondAuthorLastName = (secondAuthorFullName.indexOf("_")>0)?secondAuthorFullName.substring(secondAuthorFullName.indexOf("_")+1, secondAuthorFullName.length()):secondAuthorFullName;

                    } else {
                        firstAuthorFirstName = (authorsName.indexOf("_")>0)?authorsName.substring(0, authorsName.indexOf("_")):null;
                        firstAuthorLastName = (authorsName.indexOf("_")>0)?authorsName.substring(authorsName.indexOf("_")+1, authorsName.length()):authorsName;
                    }
                } else {
                    firstAuthorFirstName = "";
                    firstAuthorLastName = "UNKNOWN";
                }
                firstAuthorLastName = (firstAuthorLastName!=null)?firstAuthorLastName.replace('_', ' ')
                        : firstAuthorLastName;
                secondAuthorLastName = (secondAuthorLastName!=null)?secondAuthorLastName.replace('_', ' ')
                        : secondAuthorLastName;


                LOGGER.debug("          firstAuthorFirstName :: {}", firstAuthorFirstName);
                LOGGER.debug("          firstAuthorLastName :: {}", firstAuthorLastName);
                LOGGER.debug("          secondAuthorFirstName :: {}", secondAuthorFirstName);
                LOGGER.debug("          secondAuthorLastName :: {}", secondAuthorLastName);


//                List<Image> spineImages = imageService.createImages(APP_IMAGE_FOLDER, fileName, file);
                Author searchAuthor = new Author();
                searchAuthor.lastName = WordUtils.capitalizeFully(firstAuthorLastName);
                searchAuthor.firstName = WordUtils.capitalizeFully(firstAuthorFirstName);

                Book searchBook = new Book();
                searchBook.name = WordUtils.capitalizeFully(bookTitle);
                searchBook.subTitle = WordUtils.capitalizeFully(bookSubTitle);
                searchBook.authors.add(searchAuthor);
                if (secondAuthorLastName!=null && secondAuthorLastName.length()>0) {
                    Author secondSearchAuthor = new Author();
                    secondSearchAuthor.lastName = WordUtils.capitalizeFully(secondAuthorLastName);
                    secondSearchAuthor.firstName = WordUtils.capitalizeFully(secondAuthorFirstName);
                    searchBook.authors.add(secondSearchAuthor);
                }
                Book book = bookService.findOrCreateBook(searchBook);
                itemLocationMap.put(book, locationName);
//                for (Image image : spineImages) {
//                    image.item = book;
//                }
//                book.images.addAll(spineImages);



//                //new BookMeta(book);
//                List<Book> receivedBooks = new ArrayList<>();
//                Shelf shelf = (Shelf) locationService.findOrCreateLibraryLocation(library, locationName);
//                receivedBooks.add(book);
//                libraryService.receive(library, shelf, receivedBooks);
            }
        }
        long finishTime = System.currentTimeMillis();
        LOGGER.debug("Elapsed time to RIP IMAGE FOLDER TO BOOK ITEMS {}secs", (finishTime-startTime)/1000);
        return itemLocationMap;
    }
}
