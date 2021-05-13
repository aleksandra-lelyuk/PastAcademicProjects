//
//  main.c
//  348A3Q5
//
//  Created by Alexandra Lelyuk .
//  Parses car listings and adds them to catalogue implemented as a linked structure. Includes support methods for interacting with the catalogue.

#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <stdbool.h>

//the upper level linked structure
struct maker{
    char car_maker[20];
    int count;
    struct maker *next;
    struct car *below;
};

//lower level linked structure
struct car{
    char model[20];
    char trim[20];
    char km[20];
    char year[20];
    char type[20];
    char drive[20];
    char transmission[20];
    char stock[20];
    char status[20];
    char fuel[20];
    char set_of_features[200];
    struct car *next;
};

//prints a car struct
void printCar(struct car *c){
    printf(",%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s", c->model, c->trim, c->km, c->year, c->type, c->drive, c->transmission, c->stock, c->status, c->fuel, c->set_of_features);
}

// Accepted keys: car_maker, model, trim, >km, year, type, drive, transmission, stock, status, fuel, set_of_features)
//Value has to be ALL CAPS
void searchInventory(char key[], char value[], struct maker *head){
    printf("\n=== Searching for {'%s'} => {'%s'} ===\n", key, value);
    struct maker* m_current = head;
    struct car* c_current;
    
    if (strcmp(key, "car_maker") == 0){
        while (m_current != NULL){
            if(strcmp(m_current->car_maker, value) == 0){
                c_current = m_current->below;
                while (c_current != NULL){
                    printf("%s", m_current->car_maker);
                    printCar(c_current);
                    c_current = c_current->next;
                }
                
            }
            m_current = m_current->next;
        }
    }
    else if (strcmp(key, "model") == 0){
        while (m_current != NULL){
            c_current = m_current->below;
            while (c_current != NULL){
                if (strcmp(c_current->model, value) == 0){
                    printf("%s", m_current->car_maker);
                    printCar(c_current);
                }
                c_current = c_current->next;
            }
            m_current = m_current->next;
        }
    }
    else if (strcmp(key, "trim") == 0){
        while (m_current != NULL){
            c_current = m_current->below;
            while (c_current != NULL){
                if (strcmp(c_current->trim, value) == 0){
                    printf("%s", m_current->car_maker);
                    printCar(c_current);
                }
                c_current = c_current->next;
            }
            m_current = m_current->next;
        }
    }
    else if (strcmp(key, "km") == 0){
        while (m_current != NULL){
            c_current = m_current->below;
            while (c_current != NULL){
                if (strcmp(c_current->km, value) == 0){
                    printf("%s", m_current->car_maker);
                    printCar(c_current);
                }
                c_current = c_current->next;
            }
            m_current = m_current->next;
        }
    }
    else if (strcmp(key, "year") == 0){
        while (m_current != NULL){
            c_current = m_current->below;
            while (c_current != NULL){
                if (strcmp(c_current->year, value) == 0){
                    printf("%s", m_current->car_maker);
                    printCar(c_current);
                }
                c_current = c_current->next;
            }
            m_current = m_current->next;
        }
    }
    else if (strcmp(key, "type") == 0){
        while (m_current != NULL){
            c_current = m_current->below;
            while (c_current != NULL){
                if (strcmp(c_current->type, value) == 0){
                    printf("%s", m_current->car_maker);
                    printCar(c_current);
                }
                c_current = c_current->next;
            }
            m_current = m_current->next;
        }
    }
    else if (strcmp(key, "drive") == 0){
        while (m_current != NULL){
            c_current = m_current->below;
            while (c_current != NULL){
                if (strcmp(c_current->drive, value) == 0){
                    printf("%s", m_current->car_maker);
                    printCar(c_current);
                }
                c_current = c_current->next;
            }
            m_current = m_current->next;
        }
    }
    else if (strcmp(key, "transmission") == 0){
        while (m_current != NULL){
            c_current = m_current->below;
            while (c_current != NULL){
                if (strcmp(c_current->transmission, value) == 0){
                    printf("%s", m_current->car_maker);
                    printCar(c_current);
                }
                c_current = c_current->next;
            }
            m_current = m_current->next;
        }
    }
    else if (strcmp(key, "stock") == 0){
        while (m_current != NULL){
            c_current = m_current->below;
            while (c_current != NULL){
                if (strcmp(c_current->stock, value) == 0){
                    printf("%s", m_current->car_maker);
                    printCar(c_current);
                }
                c_current = c_current->next;
            }
            m_current = m_current->next;
        }
    }
    else if (strcmp(key, "status") == 0){
        while (m_current != NULL){
            c_current = m_current->below;
            while (c_current != NULL){
                if (strcmp(c_current->status, value) == 0){
                    printf("%s", m_current->car_maker);
                    printCar(c_current);
                }
                c_current = c_current->next;
            }
            m_current = m_current->next;
        }
    }
    else if (strcmp(key, "fuel") == 0){
        while (m_current != NULL){
            c_current = m_current->below;
            while (c_current != NULL){
                if (strcmp(c_current->fuel, value) == 0){
                    printf("%s", m_current->car_maker);
                    printCar(c_current);
                }
                c_current = c_current->next;
            }
            m_current = m_current->next;
        }
    }
    else if (strcmp(key, "trim") == 0){
        while (m_current != NULL){
            c_current = m_current->below;
            while (c_current != NULL){
                if (strstr(c_current->set_of_features, value) != NULL){
                    printf("%s", m_current->car_maker);
                    printCar(c_current);
                }
                c_current = c_current->next;
            }
            m_current = m_current->next;
        }
    }
    else{
        printf("Invalid key!\n");
    }
    printf("== Exiting Search... ==\n");
}

//saves the catalogue to file
void saveCatalogue2File(struct maker *m_head){
    printf("=== Saving Catalogue to File... ===\n");
    FILE *f;

    f = fopen("cOutput.txt","w");

    if(f == NULL)
    {
        printf("Error!");
        exit(1);
    }
    
    struct maker* m_current = m_head;
    struct car* c_current;
    
    while (m_current != NULL){
        c_current = m_current->below;
        while (c_current != NULL){
            fprintf(f, "%s", m_current->car_maker);
            fprintf(f, ",%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s", c_current->model, c_current->trim, c_current->km, c_current->year, c_current->type, c_current->drive, c_current->transmission, c_current->stock, c_current->status, c_current->fuel, c_current->set_of_features);
            c_current = c_current->next;
        }
        //printf("DEBUG done printing %s \n", m_current->car_maker);
        m_current = m_current->next;
    }
    fclose(f);
}

//Processes listing for a car struct
void processListing(struct car *c, char listing[]){
   
    char *token = strtok(listing, ",");//go through elements of string seperated by comma

    token = strtok(NULL, ","); //skip maker
    
    
    //assign ordered input to struct variables
    strcpy(c->model, token);
    token = strtok(NULL, ",");
    
    strcpy(c->trim, token);
    token = strtok(NULL, ",");
    
    strcpy(c->km, token);
    token = strtok(NULL, ",");
    
    strcpy(c->year, token);
    token = strtok(NULL, ",");
    
    strcpy(c->type, token);
    token = strtok(NULL, ",");
    
    strcpy(c->drive, token);
    token = strtok(NULL, ",");
    
    strcpy(c->transmission, token);
    token = strtok(NULL, ",");
    
    strcpy(c->stock, token);
    token = strtok(NULL, ",");
    
    strcpy(c->status, token);
    token = strtok(NULL, ",");
    
    strcpy(c->fuel, token);
    token = strtok(NULL, "");
    
    strcpy(c->set_of_features, token);
    
    //DEBUG printCar(c);
}

//add a listing to the catalogue
//Accepts an ordered listing, the head of the struct linked list, and a boolean for whether the original input file needs to be updated
struct maker * Add2Inventory(char line[], struct maker *m_head, bool write2file){
    if (write2file == true){
        FILE *f;

        f = fopen("inputForC.txt", "a");

        if(f == NULL)
        {
            printf("Error!");
            exit(1);
        }
        
        fprintf(f, "%s\n", line);
    }
    char linecopy[500];
    struct maker *m_new;
    struct car *c_new;
    strcpy(linecopy, line);
    char *token = strtok(linecopy, ",");
    
    bool maker_found = false;
    
    //create the first car maker if head is empty
    if (m_head->car_maker[0] == '\0'){
        strcpy(m_head->car_maker, token);
        //allocate memory for head of car list
        m_head->count = 1;
        m_head->below = malloc(sizeof(struct car));
        m_head->below->next = NULL;
        
        processListing(m_head->below, line);
        
    }
    //if one maker already exists
    else{
        struct maker* m_current = m_head;
         
        int result = strcmp(m_current->car_maker, token);
        
        while (m_current != NULL){
            result = strcmp(m_current->car_maker, token);
            if (result == 0){
                m_head->count += 1;
                maker_found = true;
                
                //add new car to the sublist
                c_new = malloc(sizeof(struct car));
                c_new->next = m_current->below;
                m_current->below = c_new;
                
                processListing(m_current->below, line);
            }
            m_current = m_current->next;
        }
        //create new maker
        if(maker_found == false){

            m_new = malloc(sizeof(struct maker));
            strcpy(m_new->car_maker, token); //copy into new maker node
            m_new->next = m_head;
            m_head = m_new;
            
            m_head->count = 1;
            
            //allocate space for head of car list
            m_head->below = malloc(sizeof(struct car));
            m_head->below->next = NULL;
            
            //fill out the info for the car struct
            processListing(m_head->below, line);
        }
    }
    return m_head;
}

int main(int argc, const char * argv[]) {
    
/* NOTE!!: .txt file sequences are as follows:  (1)listings.txt -> rubyOutput.txt (catalogue in the format required by Q3)
                                                (2)listings.txt -> inputForC.txt -> cOutput.txt */
    
    //open formatted listings file. NOTE: Program accepts listings that are ALL UPPER CASE only
    FILE *fp = fopen("inputForC.txt", "r");
    if(fp == NULL) {
         perror("Unable to open file!");
         exit(1);
         
    }
    
    struct maker *m_head = NULL;
    m_head = malloc(sizeof(struct maker));
    m_head->next = NULL;
    
    //process listings from the input doc
    char line[500];
    while(fgets(line, sizeof(line), fp) != NULL) {
        m_head = Add2Inventory(line, m_head, false);
    }
    
    
    //Print catalog
    struct maker* m_current = m_head;
    struct car* c_current;
    
    printf("=== Printing the catalogue === \n");
    while (m_current != NULL){
        c_current = m_current->below;
        while (c_current != NULL){
            printf("%s", m_current->car_maker);
            printCar(c_current);
            c_current = c_current->next;
        }
        //printf("DEBUG done printing %s \n", m_current->car_maker);
        m_current = m_current->next;
    }
    printf("== End of Catalogue ==\n");
    
    // Accepted keys: car_maker, model, trim, km, year, type, drive, transmission, stock, status, fuel, set_of_features)
    //Value has to be ALL CAPS
    searchInventory("model", "CRV", m_head);
    searchInventory("car_maker", "MERCEDES", m_head);
    
    
    //Ass new car to the input file and add to catalogue
    char listing[] = "HONDA,ACCORD,SP,0KM,2021,SEDAN,AWD,AUTO,MA043829,NEW,13L/100KM,{HEATED SEATS, HEATED MIRRORS, PARKING CAMERA}";
    Add2Inventory(listing, m_head, true);
    
    //Save the cars stored in the multi linked list to file
    saveCatalogue2File(m_head);
    
    
    fclose(fp);
}
