-- Fix award name quoting for Cannes Palme d'Or
UPDATE awards
SET award_name = 'Palme d''Or'
WHERE award_name = 'Palme d\Or';
