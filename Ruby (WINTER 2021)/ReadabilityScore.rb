#Program assessing readability index of text file

def ari_to_level(ari)
    rounded = ari.floor
    
    case rounded
        when 0
            return "Score too low to classify"
        when 1
            return "5-6 Kindergarden"
        when 2
            return "6-7 First/Second Grade"
        when 3
            return "7-9 Third Grade"
        when 4
            return "9-10 Fourth Grade"
        when 5
            return "10-11 Fifth Grade"
        when 6
            return "11-12 Sixth Grade"
        when 7
            return "12-13 Seventh Grade"
        when 8
            return "13-14 Eight Grade"
        when 9
            return "14-15 Ninth Grade"
        when 10
            return "15-16 Tenth Grade"
        when 11
            return "16-17 Eleventh Grade"
        when 12
            return "17-18 Twelveth Grade"
        when 13
            return "18-24 College Student"
        else
            return "24+ Professor"
        end
end


def calcARI(doc)
    lines = File.readlines(doc) 
    line_count = lines.size
    text = lines.join
    puts text

    #count number of characters in text
    text_no_spaces = text.delete(' ')
    char_count = (text_no_spaces.length)

    #count the number of sentences
    sentences = text.split(/\.|\?|\!/)
    sentence_count = sentences.length

    word_count = (text.count "/ /") + 1 #count number of spaces and add 1 to count words

    #calculate stats and print
    puts
    puts "Total # of character: #{char_count}"
    puts "Total # of words: #{word_count}"
    puts "Total # of sentences: #{sentence_count}"
    puts

    ari = ((4.71 * (char_count.to_f/word_count.to_f)) + (0.5*(word_count.to_f/sentence_count.to_f)) - 21.43)
    rounded = ari.round(1)
    puts "Automated Readability Index: #{rounded}"
    level = ari_to_level(rounded)
    puts "Grade level: #{level}"

end

puts "== Automated Readability Index =="
puts

calcARI("paragraph.txt")
