#Program that reads, parses, and reformats car listings. The formatted listings are then added to catalogue. Methods of interacting with catalogue are presented.

#define parent class
class Car_maker
    attr_accessor :car_maker
    def to_s
        return "#{car_maker}"
    end
    def initialize(car_maker="NULL")
        @car_maker = car_maker
    end
end
#define child class
class Car < Car_maker
    
   @@total = 0
   attr_accessor :model, :trim, :km, :year, :type, :drive, :transmission, :stock, :status, :fuel, :set_of_features
   def to_s
    return "#{car_maker},#{model},#{trim},#{km},#{year},#{type},#{drive},#{transmission},#{stock},#{status},#{fuel},#{set_of_features.join(' ')}"
   end
   def initialize(car_maker:"NULL", model:"NULL", trim:"NULL", km:0, year:0, type:"NULL", drive:"NULL", transmission:"NULL", stock:"NULL", status:"NULL", fuel:"NULL", set_of_features:["NULL"])
        super(car_maker)
        @model = model
        @trim = trim
        @km = km
        @year = year
        @type = type
        @drive = drive
        @transmission = transmission
        @stock = stock
        @status = status
        @fuel = fuel
        @set_of_features = set_of_features #deal with this being an array
        @@total += 1;
        
    end
    def initialize(car_maker:"NULL", model:"NULL", trim:"NULL", km:0, year:0, type:"NULL", drive:"NULL", transmission:"NULL", stock:"NULL", status:"NULL", fuel:"NULL", set_of_features:["NULL"])
        super(car_maker)
        @model = model
        @trim = trim
        @km = km
        @year = year
        @type = type
        @drive = drive
        @transmission = transmission
        @stock = stock
        @status = status
        @fuel = fuel
        @set_of_features = set_of_features #deal with this being an array
        @@total += 1;
        
    end
    
end

#provide the name of the file to save into and the catalogue array
def convertListings2Catalogue(doc, cata)
    puts "\n-- Converting Input File Listings to the Catalogue -- \n"
    entries = File.readlines(doc)
    entries.each do |line| 
        puts "Working on provided listing:", line
        processListing(line, cata)
       
    end 
    puts "-- Completed --"
end

#search the invenotry by providing the key (car feauture) and value of the feature. Case insensitive.
def searchInventory(key, value, catalogue)
    puts "\n-- Searching for {#{key} => #{value}}... --\n"
    
    if key == "car_maker"
        catalogue.each do |car|
            if car.car_maker == value
                puts car.to_s
            end
        end
    elsif key == "model"
        catalogue.each do |car|
            if car.model == value
                puts car.to_s
            end
        end
    elsif key == "trim"
        catalogue.each do |car|
            if car.trim == value
                puts car
            end
        end
    elsif key == "km"
        catalogue.each do |car|
            if car.km == value
                puts car
            end
        end
    elsif key == "year"
        catalogue.each do |car|
            if car.year == value
                puts car
            end
        end
    elsif key == "type"
        catalogue.each do |car|
            if car.type == value
                puts car
            end
        end
    elsif key == "drive"
        catalogue.each do |car|
            if car.drive == value
                puts car
            end
        end
    elsif key == "transmission"
        catalogue.each do |car|
            if car.transmission == value
                puts car
            end
        end
    elsif key == "stock"
        catalogue.each do |car|
            if car.stock == value
                puts car
            end
        end
    elsif key == "status"
        catalogue.each do |car|
            if car.status == value
                puts car
            end
        end
    elsif key == "fuel"
        catalogue.each do |car|
            if car.fuel == value
                puts car
            end
        end
    elsif key == "set_of_features"
        catalogue.each do |car|
            str = car.set_of_features.join(' ')
            if str.include? value
                puts car
            end
        end
    else
        puts "\nProvided key is not valid \n"
    end
    puts "-- Search completed --\n"
end

#write listing to original input file and add it to catalogue
def Add2Inventory(listing, catalogue)
    puts "\n -- Adding following listing to catalogue [#{listing}]... --"
    ''' User input version
    puts "Enter the listing: "
    line = gets.chomp
    puts line'''

    #add the listing to the listings file
    File.open("listings.txt", "a") { |file| file.puts listing}
    
    
    #reformat listing and add to catalogue
    processListing(listing, catalogue)
    
end

#writes the car objects from the array to file
#writes a different file for C because current implementation requires all upper case characters
def saveCatalogue2File(catalogue)
    puts "\n-- Saving catalogue to file... --\n"
    File.open("rubyOutput.txt", "w") do |f|
        catalogue.each { |element| f.puts(element.to_s) }
    end

    File.open("inputForC.txt", "w") do |f|
        catalogue.each { |element| f.puts(element.to_s.upcase) }
    end
    puts "-- Completed --"
end

#process parses the listing, puts it into the catalogue format, and stores it in the catalogue array
def processListing(line, cata)
    if line =~ /honda/i
        this_car = Car.new(:car_maker => "Honda")
    elsif line =~ /Toyota/i
        this_car = Car.new(:car_maker => "Toyota")
    elsif line =~ /BMW/i
        this_car = Car.new(:car_maker => "BMW")
    elsif (line =~ /Mercedes/i ) || (line =~ /Mercedec/i )
        this_car = Car.new(:car_maker => "Mercedes")
    elsif line =~ /Lexus/i
        this_car = Car.new(:car_maker => "Lexus")
    else
        puts "unknown brand"
        return
    end

    
    
    other = line[(line.index('{'))..(line.index('}'))]
    
    #seperate out the list of features 
    if line.index('{') != 0
        newline = line[...(line.index('{')-1)] + line[(line.index('}')+1)..]
    else
        newline = line[...(line.index('{'))] + line[(line.index('}')+1)..]
    end
    
    #put the rest of the entries in one listing line into an array
    words = newline.strip.delete(' ').split(/\,/) 
    
    this_car.set_of_features = other.split
    
    words.each do |x|
        if x =~ /{/
            this_car.set_of_features = x
        elsif (x =~ /used/i ) || (x =~ /new/i )
            this_car.status = x
        elsif (x =~ /^\d+km$/i )
            this_car.km = x
        elsif (x =~ /.*100km$/i )
            this_car.fuel = x
        elsif (x =~ /auto/i ) || (x =~ /manual/i ) || (x =~ /steptronic/i )
            this_car.transmission = x
        elsif (x =~ /fwd/i ) || (x =~ /rwd/i ) || (x =~ /awd/i )
            this_car.drive = x
        elsif (x =~ /^\d{4}$/ )
            this_car.year = x
        elsif (x =~ /sedan/i ) || (x =~ /coupe/i ) || (x =~ /hatchback/i )|| (x =~ /station/i ) || (x =~ /SUV/i )
            this_car.type = x
        elsif (x =~ /^[a-z]{2}$/i )
            this_car.trim = x
        elsif ((x =~ /^[a-z]+.*\d+.*$/i ) || (x =~ /^\d+.*[a-z]*.*$/i)) && (x =~ /^(?!.*km$)/) 
            this_car.stock = x
        else
            this_car.model = x
        end
    end
    puts "Formatted catalogue entry:", this_car, "\n"
    cata << this_car
    return this_car.to_s
end

catalogue = Array.new

convertListings2Catalogue("listings.txt", catalogue)
puts " "


#insert the unformatted listing as a string
Add2Inventory("{AC, Heated Seats}, FWD,Sedan,55000km,Auto,used,Toyota,Corolla,PO,879FBSM,8L/100km,2010", catalogue)

#search the catalogue
searchInventory("car_maker", "Honda", catalogue)
searchInventory("set_of_features", "Keyless Entry", catalogue)

#save to catalogue
saveCatalogue2File(catalogue)
